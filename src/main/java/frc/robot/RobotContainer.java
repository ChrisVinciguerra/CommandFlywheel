package frc.robot;

import java.util.ArrayList;
import java.util.List;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.controller.*;
import edu.wpi.first.wpilibj.geometry.*;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.trajectory.*;
import frc.robot.subsystems.*;
import frc.robot.Constants.ControllerConstants;
import frc.robot.Constants.DrivetrainConstants;
import frc.robot.Constants.ControllerConstants.*;
import frc.robot.commands.limelightcommands.*;
import frc.robot.commands.drivetraincommands.*;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RamseteCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.POVButton;

public class RobotContainer {
	private final CarouselSubsystem m_carousel = new CarouselSubsystem();
	private final DrivetrainSubsystem m_drivetrain = new DrivetrainSubsystem();
	private final FeederSubsystem m_feeder = new FeederSubsystem();
	private final FlywheelSubsystem m_flywheel = new FlywheelSubsystem();
	private final LimelightSubsystem m_limelight = new LimelightSubsystem();
	private final Joystick m_driverController = new Joystick(ControllerConstants.kDriverControllerPort);
	private final ArrayList<Trajectory> m_trajectories = new ArrayList<>();

	public RobotContainer() {
		SmartDashboard.putNumber("Minimum Speed", 0);
		// SmartDashboard.putNumber("Trajectory Choice", 0);
		configureButtonBindings();
		// generateTrajectories();

		// // Drivetrain
		m_drivetrain.setDefaultCommand(
				new ArcadeDriveCommand(m_drivetrain, () -> -m_driverController.getRawAxis(Axis.kLeftY),
						() -> (m_driverController.getRawAxis(Axis.kLeftTrigger) + 1) / 2,
						() -> (m_driverController.getRawAxis(Axis.kRightTrigger) + 1) / 2));

	}

	private void configureButtonBindings() {
		// Flywheel
		new POVButton(m_driverController, DPad.kDown).whenPressed(() -> m_flywheel.setSetpoint(1000), m_flywheel);
		new POVButton(m_driverController, DPad.kLeft).whenPressed(() -> m_flywheel.setSetpoint(2500), m_flywheel);
		new POVButton(m_driverController, DPad.kUp).whenPressed(() -> m_flywheel.setSetpoint(3000), m_flywheel);
		new POVButton(m_driverController, DPad.kRight).whenPressed(() -> m_flywheel.setSetpoint(3150), m_flywheel);
		// new JoystickButton(m_driverController, Button.kLeftBumper)
		// 		.whenPressed(new LimelightFlywheelCommand(m_limelight, m_flywheel));
		new JoystickButton(m_driverController, Button.kRightBumper).whileHeld(() -> m_flywheel.setSetpoint(0),
				m_flywheel);

		// // Carousel
		// new JoystickButton(m_driverController, Button.kSquare).whenPressed(() -> m_carousel.rotate(.2), m_carousel);
		// new JoystickButton(m_driverController, Button.kTriangle).whenPressed(() -> m_carousel.rotate(0), m_carousel);

		// // Feeder
		// new JoystickButton(m_driverController, Button.kX).whenPressed(() -> m_feeder.feed(1), m_feeder);
		// new JoystickButton(m_driverController, Button.kCircle).whenPressed(() -> m_feeder.feed(0), m_feeder);

		// // Limelight
		// new JoystickButton(m_driverController, Button.kTrackpad)
		// 		.whileHeld(new LimelightTurnCommand(m_limelight, m_drivetrain));
		// new JoystickButton(m_driverController, Button.kPS)
		// 		.whileHeld(new LimelightTurnDistanceCommand(m_limelight, m_drivetrain));
	}

	// public Command getAutonomousCommand() {
	// 	int index = (int) SmartDashboard.getNumber("Trajectory Choice", 0);
	// 	if (index > m_trajectories.size()) {
	// 		return null;
	// 	}
	// 	Trajectory trajectory = m_trajectories.get(index);

	// 	RamseteCommand ramseteCommand = new RamseteCommand(trajectory, m_drivetrain::getPose, new RamseteController(),
	// 			DrivetrainConstants.kFeedForward, DrivetrainConstants.kDriveKinematics, m_drivetrain::getWheelSpeeds,
	// 			new PIDController(DrivetrainConstants.kPDriveVel, 0, 0),
	// 			new PIDController(DrivetrainConstants.kPDriveVel, 0, 0), m_drivetrain::tankDriveVolts, m_drivetrain);

	// 	return ramseteCommand.andThen(() -> m_drivetrain.tankDriveVolts(0, 0));
	// }

	// public void generateTrajectories() {
	// 	m_trajectories.add(TrajectoryGenerator.generateTrajectory(new Pose2d(0, 0, new Rotation2d(0)),
	// 			List.of(new Translation2d(1, 0), new Translation2d(2, 0)), new Pose2d(3, 0, new Rotation2d(0)),
	// 			DrivetrainConstants.kTrajectoryConfig));
				
	// 	m_trajectories.add(TrajectoryGenerator.generateTrajectory(new Pose2d(0, 0, new Rotation2d(0)),
	// 			List.of(new Translation2d(1, -.2), new Translation2d(2, .2), new Translation2d(3, -.2),
	// 					new Translation2d(4, .2)),
	// 			new Pose2d(5, 0, new Rotation2d(0)), DrivetrainConstants.kTrajectoryConfig));
	// }
}
