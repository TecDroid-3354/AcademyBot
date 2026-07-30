package org.firstinspires.ftc.teamcode.subsystems.Shooter

import com.seattlesolvers.solverslib.hardware.motors.Motor
import org.firstinspires.ftc.teamcode.utils.AngularVelocity

class ShooterConstants {
    object identification{
        val shooterLeadMotorId = "shooterLeadMotor"
        val shooterFollowerMotorId = "shooterFollowerMotor"
    }

    object physicalCharacteristics{
        val ticksPerRotation = 28

        val gearRatio = 1.0

        val minAngularVelocity = AngularVelocity.fromRpm(0.0)
        val maxAngularVelocity = AngularVelocity.fromRpm(6000/gearRatio)
    }

    object configuration{
        val motorsRunMode = Motor.RunMode.VelocityControl

        val isLeadMotorInverted = false;
        val isFollowerMotorInverted = false;

        val zeroPowerBehaviour = Motor.ZeroPowerBehavior.FLOAT

        val kP = 1.0
        val kI = 0.0
        val kD = 0.1

    }
}