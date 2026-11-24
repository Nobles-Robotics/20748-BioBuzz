package org.firstinspires.ftc.teamcode.subsystems;

import com.pedropathing.ivy.Command;
import dev.nextftc.hardware.actuators.NextMotor;
import dev.nextftc.robot.Mechanism;
import org.firstinspires.ftc.teamcode.utils.Hardware;

public class Intake implements Mechanism {
    public enum IntakeState {
        FORWARD,
        REVERSE,
        OFF
    }

    private IntakeState state = IntakeState.OFF;
    private final NextMotor motor = Hardware.intakeMotor;
    public Command setState(IntakeState newState) { return instant(() -> state = newState); }

    @Override
    public void periodic() {
        switch (state) {
            case OFF:
                motor.setThrottle(0.0);
                break;
            case FORWARD:
                motor.setThrottle(1.0);
                break;
            case REVERSE:
                motor.setThrottle(-1.0);
                break;
        }
    }
}
