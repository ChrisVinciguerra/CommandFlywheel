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
    private final CANSparkMax m_neoFlywheel;
    private final CANPIDController m_neoController;
    private final CANEncoder m_neoEncoder;
    private double m_setPoint;

    public FlywheelSubsystem() {
        // Initialize Motors
        m_neoFlywheel = new CANSparkMax(6, MotorType.kBrushless);
        m_neoFlywheel.restoreFactoryDefaults();
        m_neoFlywheel.setInverted(true);
        m_neoFlywheel.setIdleMode(IdleMode.kBrake);
        m_neoFlywheel.enableVoltageCompensation(12);
        m_neoFlywheel.setSmartCurrentLimit(40);
        m_neoController = m_neoFlywheel.getPIDController();
        m_neoEncoder = m_neoFlywheel.getEncoder();

        m_neoController.setP(FlywheelConstants.kP);
        m_neoController.setI(FlywheelConstants.kI);
        m_neoController.setD(FlywheelConstants.kD);
        m_neoController.setIZone(FlywheelConstants.kIz);
        m_neoController.setFF(FlywheelConstants.kFF);
        m_neoController.setOutputRange(FlywheelConstants.kMinOutput, FlywheelConstants.kMaxOutput);
    }

    public void periodic() {
        double speed = m_neoEncoder.getVelocity();
        SmartDashboard.putNumber("Flywheel SetPoint", m_setPoint);
        SmartDashboard.putNumber("Flywheel Speed Graph", speed);
        SmartDashboard.putNumber("Flywheel Speed", speed);
        SmartDashboard.putNumber("Flywheel Temperature", m_neoFlywheel.getMotorTemperature());
        SmartDashboard.putNumber("Flywheel Current", m_neoFlywheel.getOutputCurrent());
        SmartDashboard.putNumber("Flywheel Output", m_neoFlywheel.getAppliedOutput());
    }

    public void setSetpoint(double setPoint) {
        m_setPoint = setPoint;

        // Disable the motor completely at rest, preventing oscillations
        if (setPoint == 0) {
            m_neoFlywheel.stopMotor();
        } else {
            m_neoController.setReference(setPoint, ControlType.kVelocity);
        }
    }

    public double getSetpoint() {
        return m_setPoint;
    }

    public double getVelocity() {
        return m_neoEncoder.getVelocity();
    }
}
