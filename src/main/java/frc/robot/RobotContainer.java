package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.subsystems.*;
import frc.robot.Constants.ControllerConstants;
import frc.robot.Constants.ControllerConstants.*;
import frc.robot.commands.drivetraincommands.*;

public class RobotContainer {
	private final DrivetrainSubsystem m_drivetrain = new DrivetrainSubsystem();
	private final Joystick m_driverController = new Joystick(ControllerConstants.kDriverControllerPort);

	public RobotContainer() {
		configureButtonBindings();

		// Drivetrain
		m_drivetrain.setDefaultCommand(
				new ArcadeDriveCommand(m_drivetrain, () -> -m_driverController.getRawAxis(Axis.kLeftY),
						() -> (m_driverController.getRawAxis(Axis.kLeftTrigger) + 1) / 2,
						() -> (m_driverController.getRawAxis(Axis.kRightTrigger) + 1) / 2));
	}

	private void configureButtonBindings() {
		new JoystickButton(m_driverController, Button.kX).toggleWhenPressed(
				new VelocityDriveCommand(m_drivetrain, () -> -m_driverController.getRawAxis(Axis.kLeftY),
						() -> (m_driverController.getRawAxis(Axis.kLeftTrigger) + 1) / 2,
						() -> (m_driverController.getRawAxis(Axis.kRightTrigger) + 1) / 2));
	}
}
