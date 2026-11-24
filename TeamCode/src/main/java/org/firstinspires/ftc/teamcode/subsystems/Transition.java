package org.firstinspires.ftc.teamcode.subsystems;

import com.pedropathing.ivy.Command;
import dev.nextftc.hardware.actuators.NextMotor;
import dev.nextftc.robot.Mechanism;
import org.firstinspires.ftc.teamcode.utils.Hardware;

public class Transition implements Mechanism {
    public enum TransitionState {
        FORWARD,
        REVERSE,
        OFF
    }

    private TransitionState state = TransitionState.OFF;
    private final NextMotor motor = Hardware.transitionMotor;
    public Command setState(TransitionState newState) { return instant(() -> state = newState); }

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
