package org.firstinspires.ftc.teamcode.opmodes.teleop;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.PoseVelocity2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.MecanumDrive;


@TeleOp
public class RRfieldCentricDrive extends LinearOpMode{

    @Override
    public void runOpMode() throws InterruptedException{

        MecanumDrive drive = new MecanumDrive(hardwareMap, new Pose2d(0, 0, 0));
        //get the Heading

        double heading = 0;
        double base_heading = 2*Math.PI;



        while(opModeIsActive()) {

            waitForStart();

            heading -= gamepad1.right_stick_x;

            //check if the robot has done a 360
            if(Math.toRadians(heading) > 2*base_heading ){
                heading -= Math.toDegrees(2*base_heading);
            }



            //manipulate the direction to always be the direction you input
            //by subtracting or adding the heading from the vector.
            //Which ones are being subtracted and added needs to be tested
            Vector2d input = new Vector2d(
                    (-gamepad1.right_stick_x - heading),
                    (-gamepad1.right_stick_y - heading)
            );
            PoseVelocity2d powers = new PoseVelocity2d(input, -gamepad1.right_stick_x);


            drive.setDrivePowers(
                    powers
            );
        }

    }
}
