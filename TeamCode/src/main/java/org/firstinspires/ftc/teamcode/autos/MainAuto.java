package org.firstinspires.ftc.teamcode.autos;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Example Auto Class
 */
@Autonomous(name = "Mecanum Encoder Auto", group = "Autonomous")
public class MainAuto extends LinearOpMode {
    private DcMotor frontLeft, backLeft, frontRight, backRight;
    static final double COUNTS_PER_MOTOR_REV = 537.7; // e.g., goBILDA 312 RPM Yellow Jacket
    static final double DRIVE_GEAR_REDUCTION = 1.0;   // 1.0 if direct-drive
    static final double WHEEL_DIAMETER_INCHES = 3.78; // 96mm wheel = ~3.78 inches
    static final double COUNTS_PER_INCH = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) /
            (WHEEL_DIAMETER_INCHES * Math.PI);

    @Override
    public void runOpMode() {
        frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
        backLeft = hardwareMap.get(DcMotor.class, "backLeft");
        frontRight = hardwareMap.get(DcMotor.class, "frontRight");
        backRight = hardwareMap.get(DcMotor.class, "backRight");

        frontRight.setDirection(DcMotor.Direction.REVERSE);
        backRight.setDirection(DcMotor.Direction.REVERSE);

        resetEncoders();

        waitForStart();

        // Instead of waiting for user-inputs, we can "instruct" the robot to drive by itself!
        // Given initial inputs, our motors know how far they have driven and we can use this to calculate out information.
        driveStraight(0.5, 24.0);
        sleep(250);

        strafe(0.5, 18.0);
        sleep(250);

        strafe(0.5, -18.0);
        sleep(250);

        driveStraight(0.5, -24.0);
    }

    public void driveStraight(double speed, double inches) {
        int ticks = (int) (inches * COUNTS_PER_INCH);
        moveMotors(speed, ticks, ticks, ticks, ticks);
    }

    public void strafe(double speed, double inches) {
        int ticks = (int) (inches * COUNTS_PER_INCH * 1.1);
        moveMotors(speed, ticks, -ticks, -ticks, ticks);
    }

    public void turnTicks(double speed, int ticks) {
        moveMotors(speed, ticks, ticks, -ticks, -ticks);
    }

    private void moveMotors(double speed, int flTicks, int blTicks, int frTicks, int brTicks) {
        if (!opModeIsActive()) return;

        frontLeft.setTargetPosition(frontLeft.getCurrentPosition() + flTicks);
        backLeft.setTargetPosition(backLeft.getCurrentPosition() + blTicks);
        frontRight.setTargetPosition(frontRight.getCurrentPosition() + frTicks);
        backRight.setTargetPosition(backRight.getCurrentPosition() + brTicks);

        frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        frontLeft.setPower(Math.abs(speed));
        backLeft.setPower(Math.abs(speed));
        frontRight.setPower(Math.abs(speed));
        backRight.setPower(Math.abs(speed));

        while (opModeIsActive() &&
                (frontLeft.isBusy() && backLeft.isBusy() && frontRight.isBusy() && backRight.isBusy())) {
            telemetry.addData("Target", "%d : %d : %d : %d", flTicks, blTicks, frTicks, brTicks);
            telemetry.addData("Current", "%d : %d : %d : %d",
                    frontLeft.getCurrentPosition(), backLeft.getCurrentPosition(),
                    frontRight.getCurrentPosition(), backRight.getCurrentPosition());
            telemetry.update();
        }

        frontLeft.setPower(0);
        backLeft.setPower(0);
        frontRight.setPower(0);
        backRight.setPower(0);

        frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    private void resetEncoders() {
        frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }
}
