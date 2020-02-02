package frc.robot.commands.limelightcommands;

import frc.robot.Constants.LimelightConstants;
import frc.robot.subsystems.DrivetrainSubsystem;
import frc.robot.subsystems.LimelightSubsystem;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class LimelightTurnCommand extends CommandBase {

    private final LimelightSubsystem m_limelightSubsystem;
    private final DrivetrainSubsystem m_drivetrainSubsystem;
    private PIDController turnController;

    public LimelightTurnCommand(LimelightSubsystem limelightSubsystem, DrivetrainSubsystem drivetrainSubsystem) {
        m_limelightSubsystem = limelightSubsystem;
        m_drivetrainSubsystem = drivetrainSubsystem;
        addRequirements(drivetrainSubsystem);
    }

    public void initialize() {
        turnController = new PIDController(LimelightConstants.kTurnP, LimelightConstants.kTurnI,
                LimelightConstants.kTurnD);
        turnController.setTolerance(.1);
    }

    public void execute() {
        double turnSpeed = turnController.calculate(m_limelightSubsystem.getXAngle());
        if (Math.abs(turnSpeed) < LimelightConstants.kMinOutput) {
            turnSpeed = turnSpeed > 0 ? LimelightConstants.kMinOutput + turnSpeed
                    : -LimelightConstants.kMinOutput + turnSpeed;
        }

        if (m_limelightSubsystem.isTargetVisible() && !turnController.atSetpoint()) {
            m_drivetrainSubsystem.arcadeDrive(0, turnSpeed, 0);
        } else {
            m_drivetrainSubsystem.arcadeDrive(0, 0, 0);
        }
    }

    public void end(boolean interrputed) {
        m_drivetrainSubsystem.arcadeDrive(0, 0, 0);
    }

    public boolean isFinished() {
        return turnController.atSetpoint();
    }
}
