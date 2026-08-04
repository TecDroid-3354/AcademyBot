package org.firstinspires.ftc.teamcode.subsystems.shooter

import com.qualcomm.robotcore.hardware.HardwareMap
import com.seattlesolvers.solverslib.command.Command
import com.seattlesolvers.solverslib.command.InstantCommand
import com.seattlesolvers.solverslib.hardware.motors.MotorEx
import org.firstinspires.ftc.teamcode.subsystems.shooter.ShooterConstants.MotorConfigurations.kP
import org.firstinspires.ftc.teamcode.subsystems.shooter.ShooterConstants.MotorConfigurations.kI
import org.firstinspires.ftc.teamcode.subsystems.shooter.ShooterConstants.MotorConfigurations.kD
import org.firstinspires.ftc.teamcode.subsystems.shooter.ShooterConstants.PhysicalProperties.gearRatio
import org.firstinspires.ftc.teamcode.subsystems.shooter.ShooterConstants.PhysicalProperties.minVelocity
import org.firstinspires.ftc.teamcode.subsystems.shooter.ShooterConstants.PhysicalProperties.maxVelocity
import org.firstinspires.ftc.teamcode.subsystems.shooter.ShooterConstants.PhysicalProperties.ticksPerRotation
import org.firstinspires.ftc.teamcode.utils.AngularVelocity

class Shooter (private val hardwareMap: HardwareMap) {
    private lateinit var shooterMotor : MotorEx


    init {
        configureMotor()
    }

    private fun setVelocity(velocity: AngularVelocity){
        val clampedVelocity = velocity.rps.coerceIn(minVelocity.rps, maxVelocity.rps)

        val transformedVelocity = clampedVelocity/ gearRatio

        val velocityInTicks = transformedVelocity * ticksPerRotation.toDouble()

        shooterMotor.velocity = velocityInTicks

        shooterMotor.set(1.0)
    }

    fun setShooterVelocityCMD(velocity: AngularVelocity) : Command{
        return InstantCommand({setVelocity(velocity)})
    }

    private fun getVelocity() : AngularVelocity{
        val motorRPM = (shooterMotor.velocity / ticksPerRotation) * 60

        val subsystemRPMs = (AngularVelocity.fromRpm(motorRPM * gearRatio))

        return subsystemRPMs
    }

    private fun configureMotor(){
        shooterMotor = MotorEx(hardwareMap, ShooterConstants.Identification.SHOOTER_MOTOR_ID)
        shooterMotor.setRunMode(ShooterConstants.MotorConfigurations.motorRunMode)
        shooterMotor.setZeroPowerBehavior(ShooterConstants.MotorConfigurations.zeroPowerBehavior)
        shooterMotor.setInverted(ShooterConstants.MotorConfigurations.direction)
        shooterMotor.setVeloCoefficients(kP, kI, kD)
    }
}