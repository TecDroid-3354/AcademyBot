package org.firstinspires.ftc.teamcode.subsystems.shooter

import com.seattlesolvers.solverslib.hardware.motors.Motor
import org.firstinspires.ftc.teamcode.utils.AngularVelocity

class ShooterConstants {
    object Identification {
        val SHOOTER_MOTOR_ID = "shooterMotor"
    }

    object PhysicalProperties {
        val gearRatio = 1.0
        val ticksPerRotation = 28

        val minVelocity = AngularVelocity.fromRpm(0.0)
        val maxVelocity = AngularVelocity.fromRpm(6000/gearRatio)
    }

    object MotorConfigurations {
        val direction = true;
        val motorRunMode = Motor.RunMode.VelocityControl
        val zeroPowerBehavior = Motor.ZeroPowerBehavior.FLOAT

        val kP = 1.0
        val kI = 0.0
        val kD = 0.1

    }
}