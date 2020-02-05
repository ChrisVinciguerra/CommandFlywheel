/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import java.util.List;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.controller.*;
import edu.wpi.first.wpilibj.geometry.*;
import edu.wpi.first.wpilibj.trajectory.*;
import edu.wpi.first.wpilibj.trajectory.constraint.DifferentialDriveVoltageConstraint;
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

	public RobotContainer() {
		/// configureButtonBindings();

		// Drivetrain
		m_drivetrain.setDefaultCommand(
				new VelocityDriveCommand(m_drivetrain, () -> -m_driverController.getRawAxis(Axis.kLeftY),
						() -> (m_driverController.getRawAxis(Axis.kLeftTrigger) + 1) / 2,
						() -> (m_driverController.getRawAxis(Axis.kRightTrigger) + 1) / 2));

	}

	private void configureButtonBindings() {
		// Flywheel
		new POVButton(m_driverController, DPad.kDown).whenPressed(() -> m_flywheel.setSetpoint(1000), m_flywheel);
		new POVButton(m_driverController, DPad.kLeft).whenPressed(() -> m_flywheel.setSetpoint(1200), m_flywheel);
		new POVButton(m_driverController, DPad.kUp).whenPressed(() -> m_flywheel.setSetpoint(1400), m_flywheel);
		new POVButton(m_driverController, DPad.kRight).whenPressed(() -> m_flywheel.setSetpoint(1600), m_flywheel);
		new JoystickButton(m_driverController, Button.kLeftBumper)
				.whenPressed(new LimelightFlywheelCommand(m_limelight, m_flywheel));
		new JoystickButton(m_driverController, Button.kRightBumper).whileHeld(() -> m_flywheel.setSetpoint(0),
				m_flywheel);

		// Carousel
		new JoystickButton(m_driverController, Button.kSquare).whenPressed(() -> m_carousel.rotate(.2), m_carousel);
		new JoystickButton(m_driverController, Button.kTriangle).whenPressed(() -> m_carousel.rotate(0), m_carousel);

		// Feeder
		new JoystickButton(m_driverController, Button.kX).whenPressed(() -> m_feeder.feed(1), m_feeder);
		new JoystickButton(m_driverController, Button.kCircle).whenPressed(() -> m_feeder.feed(0), m_feeder);

		// Limelight
		new JoystickButton(m_driverController, Button.kTrackpad)
				.whileHeld(new LimelightTurnCommand(m_limelight, m_drivetrain));
		new JoystickButton(m_driverController, Button.kPS)
				.whileHeld(new LimelightTurnDistanceCommand(m_limelight, m_drivetrain));
	}

	public Command getAutonomousCommand() {
		// Create a voltage constraint to ensure we don't accelerate too fast
		var autoVoltageConstraint = new DifferentialDriveVoltageConstraint(
				new SimpleMotorFeedforward(DrivetrainConstants.ksVolts, DrivetrainConstants.kvVoltSecondsPerMeter,
						DrivetrainConstants.kaVoltSecondsSquaredPerMeter),
				DrivetrainConstants.kDriveKinematics, 10);

		// Create config for trajectory
		TrajectoryConfig config = new TrajectoryConfig(DrivetrainConstants.kMaxSpeedMetersPerSecond,
				DrivetrainConstants.kMaxAccelerationMetersPerSecondSquared)
						// Add kinematics to ensure max speed is actually obeyed
						.setKinematics(DrivetrainConstants.kDriveKinematics)
						// Apply the voltage constraint
						.addConstraint(autoVoltageConstraint);

		// An example trajectory to follow. All units in meters.
		Trajectory exampleTrajectory = TrajectoryGenerator.generateTrajectory(
				// Start at the origin facing the +X direction
				new Pose2d(0, 0, new Rotation2d(0)),
				// Pass through these two interior waypoints, making an 's' curve path
				List.of(new Translation2d(1, 0), new Translation2d(2, 0)),
				// End 3 meters straight ahead of where we started, facing forward
				new Pose2d(3, 0, new Rotation2d(0)),
				// Pass config
				config);

		RamseteCommand ramseteCommand = new RamseteCommand(exampleTrajectory, m_drivetrain::getPose,
				new RamseteController(),
				new SimpleMotorFeedforward(DrivetrainConstants.ksVolts, DrivetrainConstants.kvVoltSecondsPerMeter,
						DrivetrainConstants.kaVoltSecondsSquaredPerMeter),
				DrivetrainConstants.kDriveKinematics, m_drivetrain::getWheelSpeeds,
				new PIDController(DrivetrainConstants.kPDriveVel, 0, 0),
				new PIDController(DrivetrainConstants.kPDriveVel, 0, 0),
				// RamseteCommand passes volts to the callback
				m_drivetrain::tankDriveVolts, m_drivetrain);

		// Run path following command, then stop at the end.
		return ramseteCommand.andThen(() -> m_drivetrain.tankDriveVolts(0, 0));
	}
}
