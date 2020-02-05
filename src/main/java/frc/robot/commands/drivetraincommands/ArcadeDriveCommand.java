package frc.robot.commands.drivetraincommands;

import frc.robot.subsystems.DrivetrainSubsystem;
import java.util.function.DoubleSupplier;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class ArcadeDriveCommand extends CommandBase {

	private final DrivetrainSubsystem m_drivetrainSubsystem;
	private final DoubleSupplier m_speedStraight, m_speedLeft, m_speedRight;

	public ArcadeDriveCommand(DrivetrainSubsystem drivetrainSubsystem, DoubleSupplier speedStraight,
			DoubleSupplier speedLeft, DoubleSupplier speedRight) {
		m_drivetrainSubsystem = drivetrainSubsystem;
		m_speedStraight = speedStraight;
		m_speedLeft = speedLeft;
		m_speedRight = speedRight;
		addRequirements(drivetrainSubsystem);
	}

	public void execute() {
		double speedStraight = Math.abs(m_speedStraight.getAsDouble()) > .05 ? m_speedStraight.getAsDouble() : 0;
		double speedLeft = Math.abs(m_speedLeft.getAsDouble()) > .05 ? m_speedLeft.getAsDouble() : 0;
		double speedRight = Math.abs(m_speedRight.getAsDouble()) > .05 ? m_speedRight.getAsDouble() : 0;
		m_drivetrainSubsystem.tankDrive(speedStraight - speedLeft + speedRight, speedStraight + speedLeft - speedRight);
	}
}