import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

/***
 * Example of a Mecanum TeleOp
 */
@TeleOp(name = "Mecanum TeleOp", group = "TeleOp")
public class MainTeleOp extends LinearOpMode {

    @Override
    public void runOpMode() {
        // Get and initialize motors
        DcMotor frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
        DcMotor backLeft = hardwareMap.get(DcMotor.class, "backLeft");
        DcMotor frontRight = hardwareMap.get(DcMotor.class, "frontRight");
        DcMotor backRight = hardwareMap.get(DcMotor.class, "backRight");

        // Reverse right-side motors
        frontRight.setDirection(DcMotor.Direction.REVERSE);
        backRight.setDirection(DcMotor.Direction.REVERSE);

        // More setup to have the motors brake when not powered
        frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        // Wait for "Start" to be pressed on the driver station
        waitForStart();

        // This is the main "loop" of the teleop and what runs every "loop"
        while (opModeIsActive()) {
            // Get the current gamepad inputs
            double y = -gamepad1.left_stick_y;
            double x = gamepad1.left_stick_x;
            double rx = gamepad1.right_stick_x;

            // Run calculations to determine power for each motor
            double flPower = y + x + rx;
            double blPower = y - x + rx;
            double frPower = y - x - rx;
            double brPower = y + x - rx;

            double maxPower = Math.max(
                    Math.max(Math.abs(flPower), Math.abs(blPower)),
                    Math.max(Math.abs(frPower), Math.abs(brPower))
            );

            if (maxPower > 1.0) {
                flPower /= maxPower;
                blPower /= maxPower;
                frPower /= maxPower;
                brPower /= maxPower;
            }

            // Set the power for each motor
            frontLeft.setPower(flPower);
            backLeft.setPower(blPower);
            frontRight.setPower(frPower);
            backRight.setPower(brPower);

            // Print out the telemetry (information for the driver station)
            telemetry.addData("FL / FR", "%.2f / %.2f", flPower, frPower);
            telemetry.addData("BL / BR", "%.2f / %.2f", blPower, brPower);
            telemetry.update();
        }
    }
}
