package org.firstinspires.ftc.teamcode

import com.qualcomm.robotcore.hardware.HardwareMap
import com.seattlesolvers.solverslib.command.InstantCommand
import com.seattlesolvers.solverslib.command.SequentialCommandGroup
import com.seattlesolvers.solverslib.gamepad.GamepadEx
import com.seattlesolvers.solverslib.hardware.motors.CRServo
import com.seattlesolvers.solverslib.hardware.motors.CRServoEx
import com.seattlesolvers.solverslib.hardware.motors.Motor
import org.firstinspires.ftc.robotcore.external.Telemetry
import org.firstinspires.ftc.teamcode.subsystems.Indexer.Indexer
import org.firstinspires.ftc.teamcode.subsystems.Shooter.Shooter
import org.firstinspires.ftc.teamcode.subsystems.Tankdrive.TankDrive
import org.firstinspires.ftc.teamcode.utils.Alliance
import org.firstinspires.ftc.teamcode.utils.AngularVelocity
import org.firstinspires.ftc.teamcode.utils.TecDroidRobot
import org.firstinspires.ftc.teamcode.utils.extensions.a
import org.firstinspires.ftc.teamcode.utils.extensions.b
import org.firstinspires.ftc.teamcode.utils.extensions.onFalse
import org.firstinspires.ftc.teamcode.utils.extensions.onTrue

class Robot(private val alliance: Alliance, private val hardwareMap: HardwareMap, private val controller: GamepadEx, telemetry: Telemetry): TecDroidRobot(telemetry, hardwareMap) {

    /* Declare your subsystems here */

    private lateinit var indexer : Indexer

    private lateinit var shooter : Shooter

    private lateinit var tankDrive : TankDrive

    init {
        subsystemInitialization()
    }

    /* Initialize your subsystems here */
    override fun subsystemInitialization() {
        tankDrive = TankDrive(hardwareMap)

        indexer = Indexer(hardwareMap)
        shooter = Shooter(hardwareMap)

    }

    /* Initialize your teleop controller commands here */
    override fun initTeleOp() {
        tankDrive.defaultCommand = tankDrive.driveCMD(controller.leftY,controller.rightX)

        controller.a().onTrue(SequentialCommandGroup(
            indexer.enableIndexerCMD(),
            shooter.setVelocityCMD(AngularVelocity.fromRpm(6000.0))
        )).onFalse(SequentialCommandGroup(
            indexer.disableIndexerCMD(),
            shooter.setVelocityCMD(AngularVelocity.fromRpm(0.0))
        ))
    }

    /* Initialize your auto commands here, set chassis alliance and starting pose */
    override fun initAuto() {}

    /* When the teleop ends, declare what to do */
    override fun onEnd() {}

    // Print telemetry using the pTelemetry object on RobotConstants.Telemetry. It will be printed on both Panels and Driver Hub.
    override fun printTelemetry() {}
}