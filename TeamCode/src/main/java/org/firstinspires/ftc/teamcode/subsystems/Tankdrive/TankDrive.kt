package org.firstinspires.ftc.teamcode.subsystems.Tankdrive

import com.qualcomm.robotcore.hardware.HardwareMap
import com.seattlesolvers.solverslib.command.Command
import com.seattlesolvers.solverslib.command.InstantCommand
import com.seattlesolvers.solverslib.command.Subsystem
import com.seattlesolvers.solverslib.hardware.motors.MotorEx
import java.util.function.Supplier
import kotlin.math.abs

class TankDrive(private val hardwareMap : HardwareMap) : Subsystem {
    private lateinit var leftMotor : MotorEx
    private lateinit var rightMotor : MotorEx

    init {
        configureMotors()
    }

    private fun configureMotors(){
        leftMotor = MotorEx(hardwareMap, DriveConstants.identification.leftDriveMotor)
        rightMotor = MotorEx(hardwareMap, DriveConstants.identification.rightDriveMotor)

        leftMotor.setRunMode(DriveConstants.configuration.motorsRunMode)
        rightMotor.setRunMode(DriveConstants.configuration.motorsRunMode)

        leftMotor.inverted = DriveConstants.configuration.isLeftMotorInverted
        rightMotor.inverted = DriveConstants.configuration.isRightMotorInverted
    }

    fun drive(leftJoystick : Double, rightJoystick : Double){
        var leftMotorPower : Double
        var rightMotorPower : Double


        val traslation = leftJoystick
        val rotation = rightJoystick

        leftMotorPower = (traslation + rotation).coerceIn(-1.0, 1.0)
        rightMotorPower = (traslation - rotation).coerceIn(-1.0,1.0)

        leftMotor.set(leftMotorPower)
        rightMotor.set(rightMotorPower)



    }

    fun driveCMD(leftJoystick : Double, rightJoystick : Double): Command{
        return InstantCommand({ drive(leftJoystick, rightJoystick) })
    }

}
