package org.firstinspires.ftc.teamcode.opmodes.teleop;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.PoseVelocity2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.IMU;


import com.acmerobotics.roadrunner.MecanumKinematics;


import org.firstinspires.ftc.teamcode.subsystems.example.Intake;
import org.firstinspires.ftc.teamcode.subsystems.example.SampleLimelight;
import org.firstinspires.ftc.teamcode.subsystems.example.SampleVision;
import org.firstinspires.ftc.teamcode.subsystems.example.ShooterSubsystem;
import org.firstinspires.ftc.teamcode.MecanumDrive;


import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teamcode.MecanumDrive;


@TeleOp

public class RRfieldCentricDrive extends LinearOpMode{

    int tagID = 0;

    public void setTagID(int tagID){
        this.tagID = tagID;
    }


    @Override
    public void runOpMode() throws InterruptedException{

        SampleLimelight limelight = new SampleLimelight(hardwareMap);
        MecanumDrive drive = new MecanumDrive(hardwareMap, new Pose2d(0, 0, 0));
        Intake intake = new Intake(hardwareMap);
        ShooterSubsystem shooter = new ShooterSubsystem(hardwareMap);

        //get the Heading

        waitForStart();

        while(opModeIsActive()) {

            drive.updatePoseEstimate();
            Pose2d pose = drive.localizer.getPose();
            double x_pos = pose.position.x;
            double y_pos = pose.position.y;
            double heading = pose.heading.toDouble();
            double xInput = -gamepad1.left_stick_y;
            double yInput = -gamepad1.left_stick_x;

            double new_x, new_y;

            if (gamepad1.dpad_down){

               heading = 0;
            }

            //can be changed to robot oriented if you change fieldOriented in MecanumDrive
            if (drive.PARAMS.fieldOriented) {
                new_x = xInput * Math.cos(heading) - yInput * Math.sin(heading);
                new_y = xInput * Math.sin(heading) + yInput * Math.cos(heading);
            }else{
                new_x = xInput;
                new_y = yInput;
            }

            Vector2d input = new Vector2d(
                    (new_x),
                    (new_y)
            );
            PoseVelocity2d powers = new PoseVelocity2d(input, -gamepad1.right_stick_x);


            drive.setDrivePowers(
                    powers
            );


            telemetry.addData("x position", x_pos);
            telemetry.addData("y position", y_pos);
            telemetry.addData("heading", Math.toDegrees(heading));
            telemetry.update();

            //intake
            if(gamepad2.xWasPressed()){
                intake.setIntakePower(1);
            }
            if(gamepad2.xWasReleased()){
                intake.setIntakePower(0);
            }


            if(gamepad2.b){
                intake.setIntakePower(-1);
            }
            if(gamepad2.bWasReleased()){
                intake.setIntakePower(0);
            }

            //prime
            if(gamepad2.yWasPressed()) {
                shooter.runShooter(0.9);
            }
            if(gamepad2.yWasReleased()) {
                shooter.runShooter(0.3);
            }
            if(gamepad2.aWasPressed()) {
                shooter.runShooter(0.8);
            }
            if(gamepad2.aWasReleased()) {
                shooter.runShooter(0.3);
            }



            if(gamepad2.dpad_down){
                intake.setPopUpPos(0);
            }
            if(gamepad2.dpad_up){
                intake.setPopUpPos(180);
            }


            //turret
            if(gamepad2.left_bumper){
                shooter.aim(.5);
            }else if(gamepad2.right_bumper){
                shooter.aim(-.5);
            }else{
                shooter.aim(-0);
            }

            //limelight
            if(limelight.getresult() != null){
                if(limelight.getresult().isValid()){

                    telemetry.addData("tx",limelight.getresult().getTx());
                    telemetry.addData("ty", limelight.getresult().getTy());

                    telemetry.update();

                }

            }

        }

    }
}
