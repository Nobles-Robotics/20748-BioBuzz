# Decode Template

Welcome! If this is your first time working with Java / GitHub, do not worry, this walks you through setting up your code editor, connecting to the robot, and pushing code to it.

| Library | What it does |
| --- | --- |
| [Pedro Pathing](https://pedropathing.com/) | Autonomous path following + tuning OpModes (`TeamCode/src/main/java/org/firstinspires/ftc/teamcode/pedro`) |
| [NextFTC](https://nextftc.dev/) | Command-based structure for TeleOp and Auto |
| [Panels](https://panels.bylazar.com/) | Live telemetry / field dashboard in your browser |
| [Sloth](https://github.com/Dairy-Foundation/Sloth) | Hot-reloads your code onto the robot in seconds instead of minutes |

You write your code in `TeamCode/`.

---

## Setup

### 1. Install Android Studio
1. Download **Android Studio** from <https://developer.android.com/studio> and install it.
   Pick the regular (stable) release.
2. Open Android Studio. Accept the default ("Standard") install when it asks, and accept the Android SDK licenses.
3. Open this project: **File → Open**, then select this folder.

### 2. Run the robot setup script

On the left side of Android Studio, in the **Terminal** tab, run:

```bash
bash robot.sh setup
```

- If it asks for your password, type it and press Enter. **The letters will not show up** — no dots, no stars. That's normal; just type it and hit Enter.
- When it prints `done.`, **close and reopen your terminal** so the `robot` command is found.

### 3. Set up the Gradle tasks (Sloth)

Sloth is what makes deploying code take seconds instead of minutes, but you need to setup the grade tasks.

1. Open the configuration dropdown in the toolbar → **Edit Configurations…**
2. Click **+ (Add new configuration)** → **Gradle**
3. In **Tasks and arguments**, type `deploySloth`
4. Name it something like `Deploy Sloth` and click **OK**

**Then edit the `TeamCode` configuration:**

1. Open **Edit Configurations…** again and select **TeamCode**
2. Click **Add new Gradle task** (under "Before launch")
3. Set **Tasks** to `removeSlothRemote`
4. Set **Gradle project** to `:TeamCode` — type it in, don't copy it from a screenshot
5. Drag `removeSlothRemote` to the **top** of the before-launch list, then click **OK**

Full details with screenshots: [Sloth → Gradle Tasks](https://github.com/Dairy-Foundation/Sloth#gradle-tasks)

### 4. Check that it works

Build the project without a robot attached:

```bash
./gradlew :TeamCode:assembleDebug
```

If that ends in `BUILD SUCCESSFUL`, your setup is good.

---

## Connecting to the robot

1. On your laptop, join the Control Hub's Wi-Fi network (we'll give you the password + network name)
2. Connect ADB:

   ```bash
   robot connect
   ```

   That defaults to `192.168.43.1:5555`, which is the standard Control Hub address. For a
   different address, pass it in: `robot connect 192.168.43.1`
3. Select "Deploy Sloth" and hit the ▶ button in Android Studio. 
4. When you're done:

   ```bash
   robot disconnect
   ```
    
    **Always disconnect from the robot when you're done before you switch off of the wifi network, or else noone else will be able to connect!**
