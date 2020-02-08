package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.DrivetrainConstants;

public class DrivetrainSubsystem extends SubsystemBase {

  private final WPI_TalonSRX m_leftTop, m_leftFront, m_rightTop, m_rightFront;

  public DrivetrainSubsystem() {
    m_leftTop = new WPI_TalonSRX(DrivetrainConstants.kLeftTopPort);
    m_leftTop.setInverted(DrivetrainConstants.kLeftTopInvert);
    m_leftFront = new WPI_TalonSRX(DrivetrainConstants.kLeftFrontPort);
    m_leftFront.setInverted(DrivetrainConstants.kLeftFrontInvert);
    m_leftFront.follow(m_leftTop);

    m_rightTop = new WPI_TalonSRX(DrivetrainConstants.kRightTopPort);
    m_rightTop.setInverted(DrivetrainConstants.kRightTopInvert);
    m_rightFront = new WPI_TalonSRX(DrivetrainConstants.kRightFrontPort);
    m_rightFront.setInverted(DrivetrainConstants.kRightFrontInvert);
    m_rightFront.follow(m_rightTop);
  }

  public void tankDrive(double leftSpeed, double rightSpeed) {
    m_leftTop.set(leftSpeed);
    m_rightTop.set(rightSpeed);
    m_leftTop.feed();
    m_rightTop.feed();
  }

  public void tankDriveVolts(double leftVolts, double rightVolts) {
    m_leftTop.setVoltage(leftVolts);
    m_rightTop.setVoltage(rightVolts);
    m_leftTop.feed();
    m_rightTop.feed();
    System.out.println("Left Output Percent: " + m_leftTop.getMotorOutputPercent());
    System.out.println("Right Output Percent: " + m_rightTop.getMotorOutputPercent());
  }
}