package frc.robot;

import com.ctre.phoenix.motorcontrol.InvertType;

import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveKinematics;
import edu.wpi.first.wpilibj.trajectory.TrajectoryConfig;
import edu.wpi.first.wpilibj.trajectory.constraint.DifferentialDriveVoltageConstraint;

public final class Constants {
    public static final class LoggingConstants {
        public static final boolean kEnableFlywheelLogging = false;
        public static final boolean kEnableDrivetrainLogging = true;
        public static final boolean kEnableLimelightLogging = false;
    }

    public static final class CarouselConstants {
        public static final int kFeedPort = 7;
        public static final int kRotatePort = 11;
    }

    public static final class DrivetrainConstants {
        public static final int kLeftTopPort = 2;
        public static final InvertType kLeftTopInvert = InvertType.InvertMotorOutput;
        public static final int kLeftFrontPort = 4;
        public static final InvertType kLeftFrontInvert = InvertType.FollowMaster;
        public static final int kRightTopPort = 1;
        public static final InvertType kRightTopInvert = InvertType.InvertMotorOutput;
        public static final int kRightFrontPort = 3;
        public static final InvertType kRightFrontInvert = InvertType.FollowMaster;

        public static final SPI.Port kGyroPort = SPI.Port.kMXP;
        // Gyro should increase as it turns ccw
        public static final boolean kGyroReversed = true;

        public static final double ksVolts = .77;
        public static final double kvVoltSecondsPerMeter = 5.84;
        public static final double kaVoltSecondsSquaredPerMeter = .627;
        public static final double kPDriveVel = 1.69;
        public static final double kTrackwidthMeters = 0.713288;
        public static final double kMaxSpeedMetersPerSecond = 2;
        public static final double kMaxAccelerationMetersPerSecondSquared = .6;
        public static final double kMaxRotSpeedMetersPerSecond = 2;
        public static final double kRamseteB = 2;
        public static final double kRamseteZeta = .7;
        public static final double kWheelDiameterMeters = .1524;
        public static final double kEncoderEdgesPerRotation = 4106;

        public static final DifferentialDriveKinematics kDriveKinematics = new DifferentialDriveKinematics(
                kTrackwidthMeters);
        public static final SimpleMotorFeedforward kFeedForward = new SimpleMotorFeedforward(
                DrivetrainConstants.ksVolts, DrivetrainConstants.kvVoltSecondsPerMeter,
                DrivetrainConstants.kaVoltSecondsSquaredPerMeter);
        public static final DifferentialDriveVoltageConstraint kVoltageConstraint = new DifferentialDriveVoltageConstraint(
                DrivetrainConstants.kFeedForward, DrivetrainConstants.kDriveKinematics, 10);
        public static final TrajectoryConfig kTrajectoryConfig = new TrajectoryConfig(
                DrivetrainConstants.kMaxSpeedMetersPerSecond,
                DrivetrainConstants.kMaxAccelerationMetersPerSecondSquared)
                        .setKinematics(DrivetrainConstants.kDriveKinematics)
                        .addConstraint(DrivetrainConstants.kVoltageConstraint);
    }

    public static final class FlywheelConstants {
        public static final int kFlywheelPort = 6;
        public static final double kP = 5e-3;
        public static final double kI = 0;
        public static final double kD = 0;
        public static final double kIz = 0;
        public static final double kFF = .00019;
        public static final double kMaxOutput = 1;
        public static final double kMinOutput = -1;
        public static final double kMaxRPM = 5700;
    }

    public static final class LimelightConstants {
        public static final double kDisP = .016;
        public static final double kDisI = 0;
        public static final double kDisD = 0;
        public static final double kTurnP = 0.022;
        public static final double kTurnI = 0.00001;
        public static final double kTurnD = 0;
        public static final double kCameraHeight = 27.6;
        public static final double kCameraAngle = 18.43;
        public static final double kTargetHeight = 89.75;// 6ft, 9.25in + 8.5 in
        public static final double kMinOutput = .13;
    }

    public static final class ControllerConstants {
        public static final int kDriverControllerPort = 0;
        public static final int kOperatorControllerPort = 1;

        public static final class Axis {
            public static final int kLeftX = 0;
            public static final int kLeftY = 1;
            public static final int kRightX = 2;
            public static final int kLeftTrigger = 3;
            public static final int kRightTrigger = 4;
            public static final int kRightY = 5;
        }

        public static final class Button {
            public static final int kSquare = 1;
            public static final int kX = 2;
            public static final int kCircle = 3;
            public static final int kTriangle = 4;
            public static final int kLeftBumper = 5;
            public static final int kRightBumper = 6;
            public static final int kShare = 9;
            public static final int kOptions = 10;
            public static final int kLeftStick = 11;
            public static final int kRightStick = 12;
            public static final int kPS = 13;
            public static final int kTrackpad = 14;
        }

        public static final class DPad {
            public static final int kUp = 0;
            public static final int kRight = 90;
            public static final int kDown = 180;
            public static final int kLeft = 270;
        }
    }
}