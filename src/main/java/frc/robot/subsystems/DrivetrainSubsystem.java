package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveWheelSpeeds;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.DrivetrainConstants;
import frc.robot.Constants.LoggingConstants;

public class DrivetrainSubsystem extends SubsystemBase {

  private final WPI_TalonSRX m_leftTop, m_rightTop;
  private WPI_VictorSPX m_leftFront, m_rightFront;
  private final SpeedControllerGroup m_leftMotors, m_rightMotors;
  private final DifferentialDrive m_drive;
  private final AHRS m_gyro;
  private final DifferentialDriveOdometry m_odometry;

  public DrivetrainSubsystem() {
    m_leftTop = new WPI_TalonSRX(DrivetrainConstants.kLeftTopPort);
    m_leftTop.setInverted(DrivetrainConstants.kLeftTopInvert);
    m_leftFront = new WPI_VictorSPX(DrivetrainConstants.kLeftFrontPort);
    m_leftFront.setInverted(DrivetrainConstants.kLeftFrontInvert);
    m_rightTop = new WPI_TalonSRX(DrivetrainConstants.kRightTopPort);
    m_rightTop.setInverted(DrivetrainConstants.kRightTopInvert);
    m_rightFront = new WPI_VictorSPX(DrivetrainConstants.kRightFrontPort);
    m_rightFront.setInverted(DrivetrainConstants.kRightFrontInvert);

    m_leftMotors = new SpeedControllerGroup(m_leftTop, m_leftFront);
    m_rightMotors = new SpeedControllerGroup(m_rightTop, m_rightFront);

    m_gyro = new AHRS(DrivetrainConstants.kGyroPort);

    resetEncoders();

    m_odometry = new DifferentialDriveOdometry(Rotation2d.fromDegrees(getHeading()));

    m_drive = new DifferentialDrive(m_leftMotors, m_rightMotors);
  }

  public void periodic() {
    m_odometry.update(Rotation2d.fromDegrees(getHeading()), getLeftEncoderDistance(), getRightEncoderDistance());

    if (LoggingConstants.kEnableDrivetrainLogging) {
      SmartDashboard.putNumber("Heading", getHeading());
      SmartDashboard.putNumber("Left Encoder Position", getLeftEncoderDistance());
      SmartDashboard.putNumber("Right Encoder Position", getRightEncoderDistance());
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

  public void arcadeDrive(double fwd, double rot) {
    m_drive.arcadeDrive(fwd, rot);
  }

  public void tankDriveVolts(double leftVolts, double rightVolts) {
    SmartDashboard.putNumber("Left Voltage", leftVolts);
    SmartDashboard.putNumber("Right Voltage", -rightVolts);

    m_leftMotors.setVoltage(leftVolts);
    m_rightMotors.setVoltage(-rightVolts);
    m_drive.feed();
  }

  public void resetEncoders() {
    m_leftTop.setSelectedSensorPosition(0);
    m_rightTop.setSelectedSensorPosition(0);
  }

  public double getLeftEncoderDistance() {
    return m_leftTop.getSelectedSensorPosition() * Math.PI * DrivetrainConstants.kWheelDiameterMeters
        / DrivetrainConstants.kEncoderEdgesPerRotation;
  }

  public double getRightEncoderDistance() {
    return m_rightTop.getSelectedSensorPosition() * Math.PI * DrivetrainConstants.kWheelDiameterMeters
        / DrivetrainConstants.kEncoderEdgesPerRotation;
  }

  public double getAverageEncoderDistance() {
    return (getLeftEncoderDistance() + getRightEncoderDistance()) / 2.0;
  }

  public double getLeftEncoderVelocity() {
    return m_leftTop.getSelectedSensorVelocity() * 10 * Math.PI * DrivetrainConstants.kWheelDiameterMeters
        / DrivetrainConstants.kEncoderEdgesPerRotation;
  }

  public double getRightEncoderVelocity() {
    return m_rightTop.getSelectedSensorVelocity() * 10 * Math.PI * DrivetrainConstants.kWheelDiameterMeters
        / DrivetrainConstants.kEncoderEdgesPerRotation;
  }

  public void setMaxOutput(double maxOutput) {
    m_drive.setMaxOutput(maxOutput);
  }

  public void zeroHeading() {
    m_gyro.reset();
  }

  public double getHeading() {
    return Math.IEEEremainder(m_gyro.getAngle(), 360) * (DrivetrainConstants.kGyroReversed ? -1.0 : 1.0);
  }

  public double getTurnRate() {
    return m_gyro.getRate() * (DrivetrainConstants.kGyroReversed ? -1.0 : 1.0);
  }
}