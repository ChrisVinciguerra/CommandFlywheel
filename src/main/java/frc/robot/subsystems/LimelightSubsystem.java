package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class LimelightSubsystem extends SubsystemBase {

    private final NetworkTable limelightTable;

    public LimelightSubsystem() {
        limelightTable = NetworkTableInstance.getDefault().getTable("limelight");
    }

    public boolean isTarget() {
        return limelightTable.getEntry("tv").getDouble(0) == 1;
    }

    public double getXAngle() {
        return limelightTable.getEntry("tx").getDouble(0);
    }

    public double getYAngle() {
        return limelightTable.getEntry("ty").getDouble(0);
    }

    public void turnOnLight() {
        limelightTable.getEntry("ledmode").setNumber(1);
    }

    public void turnOffLight() {
        limelightTable.getEntry("ledmode").setNumber(0);
    }
}
