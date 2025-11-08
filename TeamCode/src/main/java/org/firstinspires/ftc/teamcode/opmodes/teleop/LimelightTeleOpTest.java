package org.firstinspires.ftc.teamcode.opmodes.teleop;

import com.acmerobotics.roadrunner.Pose2d;
import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.LLResultTypes;
import com.qualcomm.hardware.limelightvision.LLStatus;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.subsystems.example.SampleLimelight;

@TeleOp

public class LimelightTeleOpTest extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException{
        SampleLimelight limelight = new SampleLimelight(hardwareMap);


        waitForStart();

        while(opModeIsActive()){
            Pose2d botpose = limelight.getRobotPos();
            telemetry.addData("Pose2d that the limelight gives", botpose);
            telemetry.update();
        }


    }



}
