package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.CommandScheduler;

public class Robot extends TimedRobot {

  public void robotInit() {
    new RobotContainer();
  }

  public void robotPeriodic() {
    CommandScheduler.getInstance().run();
  }
}
