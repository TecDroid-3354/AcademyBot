package org.firstinspires.ftc.teamcode.subsystems.intake

import com.qualcomm.robotcore.hardware.HardwareMap
import com.seattlesolvers.solverslib.command.Command
import com.seattlesolvers.solverslib.command.InstantCommand
import com.seattlesolvers.solverslib.hardware.motors.MotorEx
import org.firstinspires.ftc.teamcode.subsystems.intake.IntakeConstants.MotorConfigurations.kP
import org.firstinspires.ftc.teamcode.subsystems.intake.IntakeConstants.MotorConfigurations.kI
import org.firstinspires.ftc.teamcode.subsystems.intake.IntakeConstants.MotorConfigurations.kD
import org.firstinspires.ftc.teamcode.subsystems.intake.IntakeConstants.PhysicalProperties.gearRatio
import org.firstinspires.ftc.teamcode.subsystems.intake.IntakeConstants.PhysicalProperties.maxVelocity
import org.firstinspires.ftc.teamcode.subsystems.intake.IntakeConstants.PhysicalProperties.minVelocity
import org.firstinspires.ftc.teamcode.subsystems.intake.IntakeConstants.PhysicalProperties.ticksPerRotation
import org.firstinspires.ftc.teamcode.utils.AngularVelocity


class Intake (private val hardwareMap: HardwareMap) {

    private lateinit var intakeMotor: MotorEx

    init {
        configureMotor()
    }

    private fun setVelocity(velocity: AngularVelocity){
        val clampedVelocity = velocity.rps.coerceIn(minVelocity.rps, maxVelocity.rps)

        val transformedVelocity = clampedVelocity/gearRatio

        val velocityInTicks = transformedVelocity * ticksPerRotation.toDouble()

        intakeMotor.velocity = velocityInTicks

        intakeMotor.set(1.0)
    }

    fun setIntakeVelocityCMD(velocity: AngularVelocity) : Command{
        return InstantCommand({setVelocity(velocity)})
    }

    private fun getVelocity() : AngularVelocity{
        val motorRPM = (intakeMotor.velocity / ticksPerRotation) * 60

        val subsystemRPMs = AngularVelocity.fromRpm(motorRPM * gearRatio)

        return subsystemRPMs
    }

    private fun configureMotor(){
       intakeMotor = MotorEx(hardwareMap, IntakeConstants.Identification.INTAKE_MOTOR_ID)
        intakeMotor.setRunMode(IntakeConstants.MotorConfigurations.motorRunMode)
        intakeMotor.setZeroPowerBehavior(IntakeConstants.MotorConfigurations.zeroPowerBehavior)
        intakeMotor.setInverted(IntakeConstants.MotorConfigurations.direction)
        intakeMotor.setVeloCoefficients(kP, kI, kD)
    }


}




