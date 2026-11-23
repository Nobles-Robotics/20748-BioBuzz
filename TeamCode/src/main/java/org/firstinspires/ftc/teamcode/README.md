# TeamCode

This is the folder you write your robot code in. You will basically never touch anything besides this folder.

There are example TeleOps and Autos (`MainTeleOp.java` and `autos/MainAuto.java`). The folders are a suggested file structure. 
Read them over and understand them!
---

## File structure

```text
teamcode/
├── MainTeleOp.java        example TeleOp (driver-controlled)
├── autos/
│   └── MainAuto.java      example Autonomous (encoder-based)
├── subsystems/            one class per mechanism on the robot
│   ├── Robot.java
│   ├── Drive.java
│   ├── Intake.java
│   └── Turret.java
├── utils/                 helpers
│   └── Alliance.java
└── pedro/                 Pedro Pathing config
```

### `MainTeleOp.java`

A mecanum TeleOp that drives the robot. It reads `gamepad1`, runs calculations and then sets the poewrs of the motor. It expects four motors named `frontLeft`, `backLeft`, `frontRight`, and `backRight`
in your robot configuration.

### `autos/MainAuto.java`

A mecanum autonomous that drives by **encoder counts** (`RUN_TO_POSITION`). The basic idea is that we can pre-program instructions for the bot to follow.

---

## Pedro Pathing

`pedro/` holds [Pedro Pathing](https://pedropathing.com/), the autonomous path-following library,
along with its tuning OpModes. For what to tune and in what order, read the docs: <https://pedropathing.com/docs/pathing>
