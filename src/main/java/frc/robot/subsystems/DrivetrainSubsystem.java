package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.DrivetrainConstants;

public class DrivetrainSubsystem extends SubsystemBase {

  private final TalonSRX topLeft, frontLeft, backLeft, topRight, frontRight, backRight;

  public DrivetrainSubsystem() {
    topLeft = new TalonSRX(DrivetrainConstants.kTopLeftPort);
    topLeft.setInverted(true);
    frontLeft = new TalonSRX(DrivetrainConstants.kFrontLeftPort);
    frontLeft.setInverted(false);
    frontLeft.follow(topLeft);
    backLeft = new TalonSRX(DrivetrainConstants.kBackLeftPort);
    backLeft.setInverted(false);
    backLeft.follow(topLeft);

    topRight = new TalonSRX(DrivetrainConstants.kTopRightPort);
    topRight.setInverted(true);
    frontRight = new TalonSRX(DrivetrainConstants.kFrontRightPort);
    frontRight.setInverted(true);
    frontRight.follow(topRight);
    backRight = new TalonSRX(DrivetrainConstants.kBackRightPort);
    backRight.setInverted(false);
    backRight.follow(topRight);
  }

  public void arcadeDrive(double speedStraight, double speedLeft, double speedRight) {
    topLeft.set(ControlMode.PercentOutput, speedStraight - speedLeft + speedRight);
    topRight.set(ControlMode.PercentOutput, speedStraight + speedLeft - speedRight);

  }
}
