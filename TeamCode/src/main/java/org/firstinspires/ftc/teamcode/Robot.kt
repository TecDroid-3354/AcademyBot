package org.firstinspires.ftc.teamcode

import com.qualcomm.robotcore.hardware.HardwareMap
import com.seattlesolvers.solverslib.command.InstantCommand
import com.seattlesolvers.solverslib.command.SequentialCommandGroup
import com.seattlesolvers.solverslib.gamepad.GamepadEx
import com.seattlesolvers.solverslib.hardware.motors.Motor
import org.firstinspires.ftc.robotcore.external.Telemetry
import org.firstinspires.ftc.teamcode.utils.Alliance
import org.firstinspires.ftc.teamcode.utils.TecDroidRobot
import org.firstinspires.ftc.teamcode.utils.extensions.a
import org.firstinspires.ftc.teamcode.utils.extensions.onFalse
import org.firstinspires.ftc.teamcode.utils.extensions.onTrue
import org.firstinspires.ftc.teamcode.subsystems.intake.Intake
import org.firstinspires.ftc.teamcode.subsystems.shooter.Shooter
import org.firstinspires.ftc.teamcode.subsystems.tankDrive.TankDrive
import org.firstinspires.ftc.teamcode.utils.AngularVelocity

class Robot(private val alliance: Alliance, private val hardwareMap: HardwareMap, private val controller: GamepadEx, telemetry: Telemetry): TecDroidRobot(telemetry, hardwareMap) {

    /* Declare your subsystems here */
    private lateinit var intake: Intake
    private lateinit var shooter: Shooter

    private lateinit var tankDrive: TankDrive


    init {
        subsystemInitialization()
    }

    /* Initialize your subsystems here */
    override fun subsystemInitialization() {
        intake = Intake(hardwareMap)
        shooter = Shooter(hardwareMap)
        tankDrive = TankDrive(hardwareMap)
    }

    /* Initialize your teleop controller commands here */
    override fun initTeleOp() {
        tankDrive.defaultCommand = tankDrive.driveCMD(controller.leftY, controller.rightX)

        controller.a().onTrue (SequentialCommandGroup(
            intake.setIntakeVelocityCMD(AngularVelocity.fromRpm(6000.0)),
            shooter.setShooterVelocityCMD(AngularVelocity.fromRpm(6000.0))
        )).onFalse (SequentialCommandGroup(
            intake.setIntakeVelocityCMD(AngularVelocity.fromRpm(0.0)),
            shooter.setShooterVelocityCMD(AngularVelocity.fromRpm(0.0))
        ))
    }

    /* Initialize your auto commands here, set chassis alliance and starting pose */
    override fun initAuto() {}

    /* When the teleop ends, declare what to do */
    override fun onEnd() {}

    // Print telemetry using the pTelemetry object on RobotConstants.Telemetry. It will be printed on both Panels and Driver Hub.
    override fun printTelemetry() {}
}