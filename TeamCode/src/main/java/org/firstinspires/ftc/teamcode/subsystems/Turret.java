package org.firstinspires.ftc.teamcode.subsystems;

import com.pedropathing.ivy.Command;
import dev.nextftc.hardware.actuators.NextMotor;
import dev.nextftc.robot.Mechanism;
import org.firstinspires.ftc.teamcode.utils.Hardware;

import static dev.nextftc.units.Units.RotationsPerMinute;

public class Turret implements Mechanism {

    private final NextMotor motor = Hardware.turretMotor;
    private final double TOLERANCE = 25;
    private double DELTA = 25;
    private double kV = 0.0;
    private double kS = 0.0;
    private double currentVelocity = 0.0;
    private double targetVelocity = 0.0;

    public Turret() {
        motor.getVelocityConstants().setKV(kV);
        motor.getVelocityConstants().setKS(kS);
    }

    @Override
    public void periodic() {
        currentVelocity = motor.getEncoderVelocity().into(RotationsPerMinute);
    }

    public void setTargetVelocity(double targetVelocity){
        motor.setVelocitySetpoint(RotationsPerMinute.of(targetVelocity));
    }
    public Command stop(){
        return instant(()-> setTargetVelocity(0));
    }

    public boolean isAtVelocity(){
        return Math.abs(currentVelocity - targetVelocity) <= TOLERANCE;
    }

    public Command incrementVelocity(){
        return instant(()-> {
            this.targetVelocity += DELTA;
            setTargetVelocity(targetVelocity);
        });
    }
    public Command decrementVelocity(){
        return instant(()-> {
            this.targetVelocity -= DELTA;
            setTargetVelocity(targetVelocity);
        });
    }
    public Command incrementKS(){
        return instant(()-> {
            kS += 0.01;
            motor.getVelocityConstants().setKS(kS);
        });
    }

    public Command decrementKS(){
        return instant(()-> {
            kS -= 0.01;
            motor.getVelocityConstants().setKV(kS);
        });
    }
}
