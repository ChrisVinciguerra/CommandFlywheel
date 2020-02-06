package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveWheelSpeeds;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.DrivetrainConstants;
import frc.robot.Constants.LoggingConstants;

public class DrivetrainSubsystem extends SubsystemBase {

  private final WPI_TalonSRX m_leftTop, m_leftFront, m_leftBack, m_rightTop, m_rightFront, m_rightBack;
  private final AHRS m_gyro;
  private final DifferentialDriveOdometry m_odometry;

  public DrivetrainSubsystem() {
    m_leftTop = new WPI_TalonSRX(DrivetrainConstants.kLeftTopPort);
    m_leftTop.setInverted(DrivetrainConstants.kLeftTopInvert);
    m_leftFront = new WPI_TalonSRX(DrivetrainConstants.kLeftFrontPort);
    m_leftFront.setInverted(DrivetrainConstants.kLeftFrontInvert);
    m_leftFront.follow(m_leftTop);
    m_leftBack = new WPI_TalonSRX(DrivetrainConstants.kLeftBackPort);
    m_leftBack.setInverted(DrivetrainConstants.kLeftBackInvert);
    m_leftBack.follow(m_leftTop);

    m_rightTop = new WPI_TalonSRX(DrivetrainConstants.kRightTopPort);
    m_rightTop.setInverted(DrivetrainConstants.kRightTopInvert);
    m_rightFront = new WPI_TalonSRX(DrivetrainConstants.kRightFrontPort);
    m_rightFront.setInverted(DrivetrainConstants.kRightFrontInvert);
    m_rightFront.follow(m_rightTop);
    m_rightBack = new WPI_TalonSRX(DrivetrainConstants.kRightBackPort);
    m_rightBack.setInverted(DrivetrainConstants.kRightBackInvert);
    m_rightBack.follow(m_rightTop);

    m_gyro = new AHRS(DrivetrainConstants.kGyroPort);

    resetEncoders();
    zeroHeading();

    m_odometry = new DifferentialDriveOdometry(Rotation2d.fromDegrees(getHeading()));
  }

  public void periodic() {
    m_odometry.update(Rotation2d.fromDegrees(getHeading()), getLeftEncoderPosition(), getRightEncoderPosition());

    if (LoggingConstants.kEnableDrivetrainLogging) {
      SmartDashboard.putNumber("Heading", getHeading());
      SmartDashboard.putNumber("Left Encoder Position", getLeftEncoderPosition());
      SmartDashboard.putNumber("Right Encoder Position", getRightEncoderPosition());
      SmartDashboard.putNumber("Left Encoder Velocity", getLeftEncoderVelocity());
      SmartDashboard.putNumber("Right Encoder Velocity", getRightEncoderVelocity());
      SmartDashboard.putNumber("Trans x", getPose().getTranslation().getX());
      SmartDashboard.putNumber("Trans y", getPose().getTranslation().getY());
      SmartDashboard.putNumber("Rot", getPose().getRotation().getDegrees());
    }
  }

  public Pose2d getPose() {
    return m_odometry.getPoseMeters();
  }

  public DifferentialDriveWheelSpeeds getWheelSpeeds() {
    return new DifferentialDriveWheelSpeeds(getLeftEncoderVelocity(), getRightEncoderVelocity());
  }

  public void resetOdometry(Pose2d pose) {
    resetEncoders();
    m_odometry.resetPosition(pose, Rotation2d.fromDegrees(getHeading()));
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
  }

  public void resetEncoders() {
    m_leftTop.setSelectedSensorPosition(0);
    m_rightTop.setSelectedSensorPosition(0);
  }

  public double getLeftEncoderPosition() {
    return m_leftTop.getSelectedSensorPosition() * Math.PI * DrivetrainConstants.kWheelDiameterMeters
        / DrivetrainConstants.kEncoderEdgesPerRotation;
  }

  public double getRightEncoderPosition() {
    return -m_rightTop.getSelectedSensorPosition() * Math.PI * DrivetrainConstants.kWheelDiameterMeters
        / DrivetrainConstants.kEncoderEdgesPerRotation;
  }

  public double getAverageEncoderDistance() {
    return (getLeftEncoderPosition() + getRightEncoderPosition()) / 2.0;
  }

  public double getLeftEncoderVelocity() {
    return m_leftTop.getSelectedSensorVelocity() * 10 * Math.PI * DrivetrainConstants.kWheelDiameterMeters
        / DrivetrainConstants.kEncoderEdgesPerRotation;
  }

  public double getRightEncoderVelocity() {
    return -m_rightTop.getSelectedSensorVelocity() * 10 * Math.PI * DrivetrainConstants.kWheelDiameterMeters
        / DrivetrainConstants.kEncoderEdgesPerRotation;
  }

  public void zeroHeading() {
    m_gyro.reset();
  }

  public double getHeading() {
    return m_gyro.getYaw() * (DrivetrainConstants.kGyroReversed ? -1.0 : 1.0);
  }

  public double getTurnRate() {
    return m_gyro.getRate() * (DrivetrainConstants.kGyroReversed ? -1.0 : 1.0);
  }
}