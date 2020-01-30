package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.DrivetrainConstants;

public class DrivetrainSubsystem extends SubsystemBase {

  private final TalonSRX m_topLeft, m_frontLeft, m_backLeft, m_topRight, m_frontRight, m_backRight;

  public DrivetrainSubsystem() {
    m_topLeft = new TalonSRX(DrivetrainConstants.kTopLeftPort);
    m_topLeft.setInverted(true);
    m_frontLeft = new TalonSRX(DrivetrainConstants.kFrontLeftPort);
    m_frontLeft.setInverted(false);
    m_frontLeft.follow(m_topLeft);
    m_backLeft = new TalonSRX(DrivetrainConstants.kBackLeftPort);
    m_backLeft.setInverted(false);
    m_backLeft.follow(m_topLeft);

    m_topRight = new TalonSRX(DrivetrainConstants.kTopRightPort);
    m_topRight.setInverted(true);
    m_frontRight = new TalonSRX(DrivetrainConstants.kFrontRightPort);
    m_frontRight.setInverted(true);
    m_frontRight.follow(m_topRight);
    m_backRight = new TalonSRX(DrivetrainConstants.kBackRightPort);
    m_backRight.setInverted(false);
    m_backRight.follow(m_topRight);
  }

  public void arcadeDrive(double speedStraight, double speedLeft, double speedRight) {
    m_topLeft.set(ControlMode.PercentOutput, speedStraight - speedLeft + speedRight);
    m_topRight.set(ControlMode.PercentOutput, speedStraight + speedLeft - speedRight);

  }
}
