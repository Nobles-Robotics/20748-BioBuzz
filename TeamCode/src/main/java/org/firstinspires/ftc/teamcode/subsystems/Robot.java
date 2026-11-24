package org.firstinspires.ftc.teamcode.subsystems;

import androidx.annotation.NonNull;
import com.pedropathing.ivy.Command;
import dev.nextftc.robot.Mechanism;
import dev.nextftc.robot.NextRobot;
import org.firstinspires.ftc.teamcode.utils.Alliance;

import java.util.Set;

import static com.pedropathing.ivy.commands.Commands.instant;

public class Robot implements NextRobot {

    public Drive drive = new Drive();
    public Intake intake = new Intake();
    public Transition transition = new Transition();
    public Turret turret = new Turret();
    public static Alliance currentAlliance = Alliance.BLUE;
    @NonNull
    @Override
    public Set<Mechanism> getMechanisms() {
        return Set.of(drive, intake, transition, turret);
    }
    public Command setAlliance(Alliance newAlliance) {
        return instant(() -> currentAlliance = newAlliance);
    }
}
