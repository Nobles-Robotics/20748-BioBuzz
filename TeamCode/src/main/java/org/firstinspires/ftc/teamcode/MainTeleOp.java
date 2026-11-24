package org.firstinspires.ftc.teamcode;

import com.pedropathing.ivy.commands.Commands;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import dev.nextftc.hardware.lynx.NextLynxModule;
import dev.nextftc.robot.drive.DriveCommands;
import dev.nextftc.robot.opmode.BlazeBulkReadHook;
import dev.nextftc.robot.opmode.BlazeOpMode;
import dev.nextftc.robot.opmode.NextTeleop;
import dev.nextftc.robot.triggers.CommandGamepad;
import dev.nextftc.telemetry.Telemetry;
import org.firstinspires.ftc.teamcode.subsystems.Robot;
import org.firstinspires.ftc.teamcode.utils.Alliance;

@TeleOp(name = "MainTeleOp", group = "TeleOp")
@NextTeleop(name = "MainTeleOp")
public class MainTeleOp extends BlazeOpMode {

    public CommandGamepad gp1 = new CommandGamepad(gamepad1);
    private final Robot robot;

    public MainTeleOp(Robot robot) {
        super(robot, new BlazeBulkReadHook(NextLynxModule.Type.EXPANSION_HUB, false));
        this.robot = robot;
    }

    @Override
    public void disabledPeriodic(){
        Telemetry.log("Alliance", Robot.currentAlliance);
        gp1.dpadLeft().onTrue(
                robot.setAlliance(Alliance.BLUE)
        );
        gp1.dpadRight().onTrue(
                robot.setAlliance(Alliance.RED)
        );
    }

    @Override
    public void start() {
        robot.drive.startDrive(gamepad1);
    }

    @Override
    public void periodic() {
        Telemetry.log("Status", "Running");

        gp1.leftBumper().whileTrue(
                Commands.infinite(() -> DriveCommands.setScalar(0.4))
                        .setEnd(end -> DriveCommands.setScalar(1.0))
        );
    }

    @Override
    public void onExpansionHubBulkData() {
        //Run PID loops here
    }
}
