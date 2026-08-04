package org.firstinspires.ftc.teamcode.subsystems.tankDrive

import com.seattlesolvers.solverslib.hardware.motors.Motor

class DriveConstants {
    object Identification{
        val leftDriveMotor = "leftDrive"
        val rightDriveMotor = "rightDrive"
    }

    object MotorConfigurations{
        val motorRunMode = Motor.RunMode.RawPower

        val isLeftMotorInverted = false
        val isRightMotorInverted = false
    }
}