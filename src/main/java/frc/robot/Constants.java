package frc.robot;

import com.ctre.phoenix.motorcontrol.InvertType;
import edu.wpi.first.wpilibj.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveKinematics;

public final class Constants {

    public static final class DrivetrainConstants {
        public static final int kLeftTopPort = 0;
        public static final InvertType kLeftTopInvert = InvertType.None;
        public static final int kLeftFrontPort = 1;
        public static final InvertType kLeftFrontInvert = InvertType.None;
        public static final int kRightTopPort = 2;
        public static final InvertType kRightTopInvert = InvertType.None;
        public static final int kRightFrontPort = 3;
        public static final InvertType kRightFrontInvert = InvertType.None;

        // TODO - Characterize 2016
        public static final double ksVolts = .77;
        public static final double kvVoltSecondsPerMeter = 5.84;
        public static final double kaVoltSecondsSquaredPerMeter = .627;
        public static final double kTrackwidthMeters = 0.713288;
        public static final double kMaxSpeedMetersPerSecond = 3;
        public static final double kMaxRotSpeedMetersPerSecond = 3;

        public static final DifferentialDriveKinematics kDriveKinematics = new DifferentialDriveKinematics(
                kTrackwidthMeters);
        public static final SimpleMotorFeedforward kFeedForward = new SimpleMotorFeedforward(
                DrivetrainConstants.ksVolts, DrivetrainConstants.kvVoltSecondsPerMeter,
                DrivetrainConstants.kaVoltSecondsSquaredPerMeter);
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