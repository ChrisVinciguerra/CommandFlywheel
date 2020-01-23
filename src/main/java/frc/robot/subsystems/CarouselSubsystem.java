package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.CarouselConstants;

public class CarouselSubsystem extends SubsystemBase {

    private final TalonSRX feedMotor;
    private final VictorSPX rotateMotor;

    public CarouselSubsystem() {
        feedMotor = new TalonSRX(CarouselConstants.kFeedPort);
        rotateMotor = new VictorSPX(CarouselConstants.kRotatePort);
    }

    public void rotate(double speed) {
        rotateMotor.set(ControlMode.PercentOutput, speed);
    }

    public void feed(double speed){
        feedMotor.set(ControlMode.PercentOutput, speed);
    }
}
