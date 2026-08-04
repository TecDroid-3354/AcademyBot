package org.firstinspires.ftc.teamcode.subsystems.tankDrive

import com.qualcomm.robotcore.hardware.HardwareMap
import com.seattlesolvers.solverslib.command.Command
import com.seattlesolvers.solverslib.command.InstantCommand
import com.seattlesolvers.solverslib.command.Subsystem
import com.seattlesolvers.solverslib.hardware.motors.MotorEx
import org.firstinspires.ftc.teamcode.subsystems.tankDrive.DriveConstants.Identification.leftDriveMotor
import org.firstinspires.ftc.teamcode.subsystems.tankDrive.DriveConstants.Identification.rightDriveMotor
import org.firstinspires.ftc.teamcode.subsystems.tankDrive.DriveConstants.MotorConfigurations.isLeftMotorInverted
import org.firstinspires.ftc.teamcode.subsystems.tankDrive.DriveConstants.MotorConfigurations.isRightMotorInverted
import org.firstinspires.ftc.teamcode.subsystems.tankDrive.DriveConstants.MotorConfigurations.motorRunMode
import java.util.function.Supplier
import kotlin.math.abs

class TankDrive (private val hardwareMap: HardwareMap) : Subsystem {
    private lateinit var leftMotor : MotorEx
    private lateinit var rightMotor : MotorEx

    init {
        configureMotors()
    }

    fun drive(leftJoystick : Double, rightJoystick : Double){
        var leftMotorPower : Double
        var rightMotorPower : Double

        val traslation = leftJoystick
        val rotation = rightJoystick

        leftMotorPower = (traslation + rotation).coerceIn(-1.0, 1.0)
        rightMotorPower = (traslation - rotation).coerceIn(-1.0,1.0)
    }

    fun driveCMD(leftJoystick: Double, rightJoystick: Double) : Command{
        return InstantCommand({drive(leftJoystick, rightJoystick)})
    }

    private fun configureMotors(){

        leftMotor = MotorEx(hardwareMap, leftDriveMotor)
        rightMotor = MotorEx(hardwareMap, rightDriveMotor)

        leftMotor.setRunMode(motorRunMode)
        rightMotor.setRunMode(motorRunMode)

        leftMotor.inverted = isLeftMotorInverted
        rightMotor.inverted = isRightMotorInverted

    }

}