package frc.robot;

public final class Constants {
    public static class CarouselConstants {
        public static final int kFeedPort = 7;
        public static final int kRotatePort = 11;
    }

    public static final class DrivetrainConstants {
        public static final int kTopLeftPort = 10;
        public static final int kFrontLeftPort = 9;
        public static final int kBackLeftPort = 8;
        public static final int kTopRightPort = 4;
        public static final int kFrontRightPort = 2;
        public static final int kBackRightPort = 3;
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