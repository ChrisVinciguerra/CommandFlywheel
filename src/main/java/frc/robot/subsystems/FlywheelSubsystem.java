package frc.robot.subsystems;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.ControlType;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.FlywheelConstants;

public class FlywheelSubsystem extends SubsystemBase {
    private final CANSparkMax neoFlywheel;
    private final CANPIDController neoController;
    private final CANEncoder neoEncoder;
    private boolean atSpeed;
    private double setPoint;

    public FlywheelSubsystem() {
        neoFlywheel = new CANSparkMax(6, MotorType.kBrushless);
        neoFlywheel.restoreFactoryDefaults();
        neoFlywheel.setInverted(true);
        neoFlywheel.setIdleMode(IdleMode.kBrake);
        neoFlywheel.enableVoltageCompensation(12);
        neoFlywheel.setSmartCurrentLimit(40);
        neoController = neoFlywheel.getPIDController();
        neoEncoder = neoFlywheel.getEncoder();

        neoController.setP(FlywheelConstants.kP);
        neoController.setI(FlywheelConstants.kI);
        neoController.setD(FlywheelConstants.kD);
        neoController.setIZone(FlywheelConstants.kIz);
        neoController.setFF(FlywheelConstants.kFF);
        neoController.setOutputRange(FlywheelConstants.kMinOutput, FlywheelConstants.kMaxOutput);
    }

    public void periodic() {
        double speed = neoEncoder.getVelocity();
        SmartDashboard.putNumber("Flywheel SetPoint", setPoint);
        SmartDashboard.putNumber("Flywheel Speed Graph", speed);
        SmartDashboard.putNumber("Flywheel Speed", speed);
        SmartDashboard.putNumber("Flywheel Temperature", neoFlywheel.getMotorTemperature());
        SmartDashboard.putNumber("Flywheel Current", neoFlywheel.getOutputCurrent());
        SmartDashboard.putNumber("Flywheel output", neoFlywheel.getAppliedOutput());

        atSpeed = setPoint == 0 ? false : Math.abs(setPoint - neoEncoder.getVelocity()) < 20;
    }

    public void setSetpoint(double setPoint) {
        this.setPoint = setPoint;
        
        // Disable the motor completely at rest, preventing oscillations
        if (setPoint == 0) {
            neoFlywheel.stopMotor();
        } else {
            neoController.setReference(setPoint, ControlType.kVelocity);
        }
    }

    public boolean isAtSpeed() {
        return atSpeed;
    }
}
