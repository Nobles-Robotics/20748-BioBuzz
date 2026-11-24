package org.firstinspires.ftc.teamcode.utils;

import dev.nextftc.hardware.RobotController;
import dev.nextftc.hardware.actuators.NextMotor;

public class Hardware {
    public static final NextMotor frontLeft = new NextMotor(RobotController.controlHub(), 1);
    public static final NextMotor frontRight = new NextMotor(RobotController.controlHub(), 2);
    public static final NextMotor backLeft = new NextMotor(RobotController.controlHub(), 3);
    public static final NextMotor backRight =  new NextMotor(RobotController.controlHub(), 4);
    public static final NextMotor intakeMotor = new NextMotor(RobotController.expansionHub(), 1);
    public static final NextMotor transitionMotor = new NextMotor(RobotController.expansionHub(), 2);
    public static final NextMotor turretMotor = new NextMotor(RobotController.expansionHub(), 3);


}
