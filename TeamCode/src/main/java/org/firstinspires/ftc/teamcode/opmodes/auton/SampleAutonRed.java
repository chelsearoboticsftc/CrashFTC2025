package org.firstinspires.ftc.teamcode.opmodes.auton;

import androidx.annotation.NonNull;

// RR-specific imports
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.TrajectoryActionBuilder;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;

// Non-RR imports
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import org.firstinspires.ftc.teamcode.MecanumDrive;
import org.firstinspires.ftc.teamcode.tuning.TuningOpModes;

@Autonomous
public class SampleAutonRed extends LinearOpMode{
    @Override
    public void runOpMode() throws InterruptedException{

        waitForStart();

            // instantiate your MecanumDrive at a particular pose.
            Pose2d initialPose = new Pose2d(-11.8, -61.7, Math.toRadians(90));
            MecanumDrive drive = new MecanumDrive(hardwareMap, initialPose);
            //wanted to see if this works without the TrajectoryBuilder object.
            drive.actionBuilder(initialPose)
                     .waitSeconds(2)
                    //wait 2 seconds, and then spline to the position (-32, -48), while (I think) we change heading.
                    //For the other crash programmers, the tangent is basically the change in y over the change in x, as a proportion.
                    //since roadrunner treats the field as a coordinate grid, I think we use this to turn the robot sometimes.
                    //We are doing this in the unit radians, where 180 degrees is equal to PI, so Math.PI/2 here just means 90 degrees.
                    //In theory. This is untested.
                     .splineTo(new Vector2d(-32, -48), Math.PI/2);

    }
}
