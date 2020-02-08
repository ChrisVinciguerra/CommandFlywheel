package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;

public class Robot extends TimedRobot {

  private Command m_autonomousCommand;
  private RobotContainer m_robotContainer;

  public void robotInit() {
    m_robotContainer = new RobotContainer();
  }

  public void robotPeriodic() {
    CommandScheduler.getInstance().run();
  }

  public void autonomousInit() {
    SmartDashboard.putBoolean("Auto", false);
    if (m_autonomousCommand != null) {
      SmartDashboard.putBoolean("Auto", true);
      m_autonomousCommand.schedule();
    }
  }

  public void teleopInit() {
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
  }

  public void testInit() {
    CommandScheduler.getInstance().cancelAll();
  }
}
