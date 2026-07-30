package org.firstinspires.ftc.teamcode.subsystems.Shooter

import com.qualcomm.robotcore.hardware.HardwareMap
import com.seattlesolvers.solverslib.command.Command
import com.seattlesolvers.solverslib.command.InstantCommand
import com.seattlesolvers.solverslib.hardware.motors.MotorEx
import org.firstinspires.ftc.teamcode.utils.AngularVelocity

class Shooter(private val hardwareMap : HardwareMap) {
    private lateinit var shooterLeadMotor : MotorEx
    private lateinit var shooterFollowerMotor : MotorEx

    init {
        motorConfig()
    }

    private fun setVelocity(velocity: AngularVelocity){
        val clampedVelocity = velocity.rps.coerceIn(ShooterConstants.physicalCharacteristics.minAngularVelocity.rps,ShooterConstants.physicalCharacteristics.maxAngularVelocity.rps)

        val transformedVelocity = clampedVelocity/ ShooterConstants.physicalCharacteristics.gearRatio

        val velocityInTicks = transformedVelocity * ShooterConstants.physicalCharacteristics.ticksPerRotation.toDouble()

        shooterLeadMotor.velocity = velocityInTicks
        shooterFollowerMotor.velocity = velocityInTicks

        shooterLeadMotor.set(1.0)
        shooterFollowerMotor.set(1.0)
    }

    fun setVelocityCMD(velocity: AngularVelocity) : Command{
        return InstantCommand({setVelocity(velocity)})
    }

    private fun getVelocity() : AngularVelocity{
        val motorRPM = (shooterLeadMotor.velocity / ShooterConstants.physicalCharacteristics.ticksPerRotation) * 60

        val subsystemRPMs = AngularVelocity.fromRpm(motorRPM * ShooterConstants.physicalCharacteristics.gearRatio)

        return subsystemRPMs
    }

    private fun motorConfig(){
        shooterLeadMotor = MotorEx(hardwareMap, ShooterConstants.identification.shooterLeadMotorId)
        shooterFollowerMotor = MotorEx(hardwareMap, ShooterConstants.identification.shooterFollowerMotorId)

        shooterLeadMotor.setRunMode(ShooterConstants.configuration.motorsRunMode)
        shooterFollowerMotor.setRunMode(ShooterConstants.configuration.motorsRunMode)

        shooterLeadMotor.setInverted(ShooterConstants.configuration.isLeadMotorInverted)
        shooterFollowerMotor.setInverted(ShooterConstants.configuration.isFollowerMotorInverted)

        shooterLeadMotor.setZeroPowerBehavior(ShooterConstants.configuration.zeroPowerBehaviour)
        shooterFollowerMotor.setZeroPowerBehavior(ShooterConstants.configuration.zeroPowerBehaviour)

        shooterLeadMotor.setVeloCoefficients(ShooterConstants.configuration.kP, ShooterConstants.configuration.kI,
            ShooterConstants.configuration.kD)
        shooterFollowerMotor.setVeloCoefficients(ShooterConstants.configuration.kP, ShooterConstants.configuration.kI,
            ShooterConstants.configuration.kD)
    }
}