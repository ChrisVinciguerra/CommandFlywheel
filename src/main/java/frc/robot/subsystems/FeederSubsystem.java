package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.CarouselConstants;

public class FeederSubsystem extends SubsystemBase {

    private final TalonSRX m_feedMotor;

    public FeederSubsystem() {
        m_feedMotor = new TalonSRX(CarouselConstants.kFeedPort);
    }

    public void feed(double speed) {
        m_feedMotor.set(ControlMode.PercentOutput, speed);
    }
}
