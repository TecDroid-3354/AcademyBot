package org.firstinspires.ftc.teamcode.subsystems.Tankdrive

import com.seattlesolvers.solverslib.hardware.motors.Motor

class DriveConstants {
    object identification {
        val leftDriveMotor = "leftDrive"
        val rightDriveMotor = "rightDrive"
    }

    object configuration{
        val motorsRunMode = Motor.RunMode.RawPower

        val isLeftMotorInverted = false
        val isRightMotorInverted = false
    }

}