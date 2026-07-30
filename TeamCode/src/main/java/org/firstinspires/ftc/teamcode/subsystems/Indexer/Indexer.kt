package org.firstinspires.ftc.teamcode.subsystems.Indexer

import com.qualcomm.robotcore.hardware.HardwareMap
import com.seattlesolvers.solverslib.command.Command
import com.seattlesolvers.solverslib.command.InstantCommand
import com.seattlesolvers.solverslib.hardware.motors.CRServoEx
import com.seattlesolvers.solverslib.hardware.servos.ServoEx
import org.firstinspires.ftc.teamcode.subsystems.Shooter.ShooterConstants

class Indexer(private val hardwareMap : HardwareMap) {
    private lateinit var indexerLeadServo : CRServoEx
    private lateinit var indexerFollowerServo : CRServoEx
    init {
        configureServos()
    }

    private fun enableIndexer(){
        indexerLeadServo.set(1.0)
        indexerFollowerServo.set(1.0)
    }

    private fun disableIndexer(){
        indexerLeadServo.set(0.0)
        indexerFollowerServo.set(0.0)
    }

    fun enableIndexerCMD() : Command{
        return InstantCommand({enableIndexer()})
    }

    fun disableIndexerCMD(): Command{
        return InstantCommand({disableIndexer()})
    }
    private fun configureServos(){
        indexerLeadServo = CRServoEx(hardwareMap, IndexerConstants.identification.leadIntakeServoId)
        indexerFollowerServo = CRServoEx(hardwareMap, IndexerConstants.identification.followerIntakeServoId)

        indexerLeadServo.inverted = ShooterConstants.configuration.isLeadMotorInverted
        indexerFollowerServo.inverted = ShooterConstants.configuration.isFollowerMotorInverted
    }
}