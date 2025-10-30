package org.firstinspires.ftc.teamcode.opmodes.teleop;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.PoseVelocity2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teamcode.MecanumDrive;


@TeleOp
@Disabled
public class RRfieldCentricDrive extends LinearOpMode{

    int tagID = 0;

    public void setTagID(int tagID){
        this.tagID = tagID;
    }


    @Override
    public void runOpMode() throws InterruptedException{

        MecanumDrive drive = new MecanumDrive(hardwareMap, new Pose2d(0, 0, 0));
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

        }

    }
}
