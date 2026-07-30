package org.firstinspires.ftc.teamcode.subsystems.Indexer

import com.qualcomm.robotcore.hardware.Servo

class IndexerConstants {
    object identification{
        val leadIntakeServoId = "leadIntakeServo"
        val followerIntakeServoId = "followerIntakeServo"
    }

    object configuration{
        val isLeadIntakeServoInverted = false
        val isFollowerIntakeServoInverted = false

    }

    object physicalCharacteristics{
        val gearRatio = 1.0
    }

}