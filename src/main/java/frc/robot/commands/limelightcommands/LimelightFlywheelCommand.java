package frc.robot.commands.limelightcommands;

import frc.robot.subsystems.FlywheelSubsystem;
import frc.robot.subsystems.LimelightSubsystem;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class LimelightFlywheelCommand extends CommandBase {

    private final LimelightSubsystem m_limelightSubsystem;
    private final FlywheelSubsystem m_flywheelSubsystem;

    public LimelightFlywheelCommand(LimelightSubsystem limelightSubsystem, FlywheelSubsystem flywheelSubsystem) {
        m_limelightSubsystem = limelightSubsystem;
        m_flywheelSubsystem = flywheelSubsystem;
        addRequirements(flywheelSubsystem);
    }

    public void initialize() {
    }

    public void execute() {
        if (m_limelightSubsystem.isTargetVisible()) {
            m_flywheelSubsystem.setSetpoint(calculateSpeed(m_limelightSubsystem.getDistance()));
        }
    }

    public double calculateSpeed(double distance) {
        // TODO - test and tune
        return 1000;
    }
}
