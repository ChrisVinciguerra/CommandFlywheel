package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class LimelightSubsystem extends SubsystemBase {

    private final NetworkTable m_limelightTable;

    public LimelightSubsystem() {
        m_limelightTable = NetworkTableInstance.getDefault().getTable("limelight");
    }

    public boolean isTargetVisible() {
        return m_limelightTable.getEntry("tv").getDouble(0) == 1;
    }

    public double getXAngle() {
        return m_limelightTable.getEntry("tx").getDouble(0);
    }

    public double getYAngle() {
        return m_limelightTable.getEntry("ty").getDouble(0);
    }

    public void turnOnLight() {
        m_limelightTable.getEntry("ledmode").setNumber(1);
    }

    public void turnOffLight() {
        m_limelightTable.getEntry("ledmode").setNumber(0);
    }
}
