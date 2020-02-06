package frc.robot.commands.drivetraincommands;

import frc.robot.Constants.DrivetrainConstants;
import frc.robot.subsystems.DrivetrainSubsystem;
import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveWheelSpeeds;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class VelocityDriveCommand extends CommandBase {

    private final DrivetrainSubsystem m_drivetrainSubsystem;
    private final DoubleSupplier m_speedStraight, m_speedLeft, m_speedRight;
    private final SimpleMotorFeedforward m_feedForward;

    public VelocityDriveCommand(DrivetrainSubsystem drivetrainSubsystem, DoubleSupplier speedStraight,
            DoubleSupplier speedLeft, DoubleSupplier speedRight) {
        m_drivetrainSubsystem = drivetrainSubsystem;
        m_speedStraight = speedStraight;
        m_speedLeft = speedLeft;
        m_speedRight = speedRight;
        m_feedForward = new SimpleMotorFeedforward(DrivetrainConstants.ksVolts,
                DrivetrainConstants.kvVoltSecondsPerMeter, DrivetrainConstants.kaVoltSecondsSquaredPerMeter);
        addRequirements(drivetrainSubsystem);
    }

    public void execute() {
        double speedStraight = Math.abs(m_speedStraight.getAsDouble()) > .1 ? m_speedStraight.getAsDouble() : 0;
        double speedLeft = Math.abs(m_speedLeft.getAsDouble()) > .1 ? m_speedLeft.getAsDouble() : 0;
        double speedRight = Math.abs(m_speedRight.getAsDouble()) > .1 ? m_speedRight.getAsDouble() : 0;
        DifferentialDriveWheelSpeeds wheelSpeeds = DrivetrainConstants.kDriveKinematics
                .toWheelSpeeds(new ChassisSpeeds(speedStraight * DrivetrainConstants.kMaxSpeedMetersPerSecond, 0,
                        (speedLeft - speedRight) * DrivetrainConstants.kMaxRotSpeedMetersPerSecond));
        double leftVoltage = m_feedForward.calculate(wheelSpeeds.leftMetersPerSecond);
        double rightVoltage = m_feedForward.calculate(wheelSpeeds.rightMetersPerSecond);
        m_drivetrainSubsystem.tankDriveVolts(leftVoltage, rightVoltage);
    }
}
