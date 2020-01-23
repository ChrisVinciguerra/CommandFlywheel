package frc.robot;

public class Constants {
    public static class CarouselConstants {
        public static final int kFeedPort = 7;
        public static final int kRotatePort = 11;
    }

    public static class DrivetrainConstants {
        public static final int kTopLeftPort = 10;
        public static final int kFrontLeftPort = 9;
        public static final int kBackLeftPort = 8;
        public static final int kTopRightPort = 4;
        public static final int kFrontRightPort = 2;
        public static final int kBackRightPort = 3;
    }

    public static class FlywheelConstants {
        public static final int kFlywheelPort = 6;
        public static final double kP = 5e-3;
        public static final double kI = 0;
        public static final double kD = 0;
        public static final double kIz = 0;
        public static final double kFF = .00019;
        public static final double kMaxOutput = 1;
        public static final double kMinOutput = -1;
    }

    public static class LimelightConstants {

    }

    public static class PS4Constants {
        public static final int kDriverControllerPort = 0;
        public static final int kOperatorControllerPort = 1;
        public static final int kLeftX = 0;
        public static final int kLeftY = 1;
        public static final int kRightX = 2;
        public static final int kLeftTrigger = 3;
        public static final int kRightTrigger = 4;
        public static final int kRightY = 5;
        public static final int kSquare = 1;
        public static final int kX = 2;
        public static final int kCircle = 3;
        public static final int kTriangle = 4;
    }
}
