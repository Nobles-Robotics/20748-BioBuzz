package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.Gamepad;
import dev.nextftc.hardware.actuators.NextMotor;
import dev.nextftc.robot.Mechanism;
import dev.nextftc.robot.drive.DriveCommands;
import org.firstinspires.ftc.teamcode.utils.Hardware;

public class Drive implements Mechanism {
    private final NextMotor frontLeft = Hardware.frontLeft;
    private final NextMotor frontRight = Hardware.frontRight;
    private final NextMotor backLeft = Hardware.backLeft;
    private final NextMotor backRight = Hardware.backRight;

    public void startDrive(Gamepad gamepad) {
        DriveCommands.mecanumDrive(frontLeft, frontRight, backLeft, backRight, gamepad).schedule();
    }
}
