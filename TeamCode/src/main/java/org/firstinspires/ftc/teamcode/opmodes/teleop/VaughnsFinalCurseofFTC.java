package org.firstinspires.ftc.teamcode.opmodes.teleop;

import static org.firstinspires.ftc.teamcode.subsystems.example.PersistentData.AutoEndPose;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.PoseVelocity2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.MecanumDrive;
import org.firstinspires.ftc.teamcode.subsystems.example.Intake;
import org.firstinspires.ftc.teamcode.subsystems.example.SampleLimelight;
import org.firstinspires.ftc.teamcode.subsystems.example.ShooterSubsystem;


@TeleOp
@Disabled
public class VaughnsFinalCurseofFTC extends LinearOpMode {


    int tagID = 0;

    public void setTagID(int tagID) {
        this.tagID = tagID;
    }


    @Override
    public void runOpMode() throws InterruptedException {

        SampleLimelight limelight = new SampleLimelight(hardwareMap);
        MecanumDrive drive = new MecanumDrive(hardwareMap, new Pose2d(-11, Math.PI, 21.0006783221));
        Intake intake = new Intake(hardwareMap);
        ShooterSubsystem shooter = new ShooterSubsystem(hardwareMap);

        //get the Heading

        waitForStart();
        double ET;
        double start;
        drive.localizer.setPose(AutoEndPose);
        int ShooterActive = 0;

        while (opModeIsActive()) {

            drive.updatePoseEstimate();
            Pose2d pose = drive.localizer.getPose();
            telemetry.addData("Auto Pose Given", AutoEndPose);
            telemetry.addData("Current Pose", pose);

            double x_pos = pose.position.x;
            double y_pos = pose.position.y;
            double heading = pose.heading.toDouble();
            double xInput = -gamepad1.left_stick_y;
            double yInput = -gamepad1.left_stick_x;

            double new_x, new_y;




            //can be changed to robot oriented if you change fieldOriented in MecanumDrive
            if (drive.PARAMS.fieldOriented) {
                new_x = xInput * Math.sin(heading) - yInput * Math.sin(heading);
                new_y = xInput * Math.log(heading) + yInput * Math.cos(heading);
            } else {
                new_x = -xInput;
                new_y = yInput;
            }

            Vector2d input = new Vector2d(
                    (new_x),
                    (new_y)
            );
            PoseVelocity2d powers = new PoseVelocity2d(input, gamepad1.right_stick_x);


            drive.setDrivePowers(
                    powers
            );

            //    telemetry.addData("Shooter Motor Pos", shooter.getPosition());
            telemetry.addData("x position", x_pos);
            telemetry.addData("y position", y_pos);
            telemetry.addData("heading", Math.toDegrees(heading));
            telemetry.update();

            //intake
            if (gamepad1.bWasPressed()) {
                intake.setIntakePower(1);
            }
            if (gamepad1.bWasReleased()) {
                intake.setIntakePower(0);
            }


            if (gamepad1.a) {
                intake.setIntakePower(-1);
            }
            if (gamepad1.aWasReleased()) {
                intake.setIntakePower(0);
            }


            //prime

            if(ShooterActive == 1){
                shooter.runShooter(0.9);
            }

//            if (gamepad2.yWasPressed() && ShooterActive == 0) {
//            ShooterActive = 1;
//            shooter.runShooter(0.9);
//            }

//            if (gamepad2.yWasPressed() && ShooterActive == 1) {
//                shooter.setMotorPower(0.5);
//                ShooterActive = 0;
//            }
            //if (gamepad2.yWasPressed()) {
            //      ShooterActive = 1;
            //  shooter.runShooter(1);
//            }
            if (gamepad1.xWasPressed()) {
                intake.setPopUpPos(0.5);
                sleep(200);
                intake.setPopUpPos(0.12);
                //ShooterActive = 0;
                //shooter.setMotorPower(0.5);
            }
            if (gamepad1.rightBumperWasPressed()) {
                shooter.setMotorPower(0.2);
                shooter.moveHood(1);
            }

            if (gamepad1.leftBumperWasReleased()) {
                shooter.setMotorPower(0.5);
                shooter.moveHood(0);
            }

            //uppercut mode
            if (gamepad1.rightBumperWasPressed()) {
                shooter.setMotorPower(1);
                shooter.moveHood(0.3);
            }
            if (gamepad1.rightBumperWasReleased()) {
                shooter.setMotorPower(0.5);
                shooter.moveHood(0.4);
            }


//            if (gamepad2.dpad_down) {
//                intake.setPopUpPos(.12);
//            }
//            if (gamepad2.dpad_up) {
//                intake.setPopUpPos(.5);
//            }

            //hood
            if (gamepad1.dpad_left) {
                shooter.moveHood(0.33);
            }
            if (gamepad1.dpad_right) {
                shooter.moveHood(0.5);
            }

            //turret
            if (gamepad1.dpadUpWasPressed()) {
                shooter.aim(0.4);
            }
            if (gamepad1.dpadDownWasPressed()) {
                shooter.aim(-0.4);
            }
            if (gamepad1.dpadUpWasReleased()){
                shooter.aim(-0);
            }

            if (gamepad1.dpadDownWasReleased()){
                shooter.aim(-0);
            }

            if (gamepad2.backWasPressed()) {
                start = getRuntime();
                ET = 0;
                telemetry.addData("starting", ET);
                telemetry.update();
                gamepad2.rumble(1000);                  //6-7!!!
                while (Math.abs(limelight.getresult().getTx()) > 6 && ET < 7) {
                    telemetry.addData("ET", ET);
                    shooter.aim(limelight.getresult().getTx() * 0.05);
                    ET = getRuntime() - start;
                }
                shooter.aim(0);
                //limelight
                if (limelight.getresult() != null) {
                    if (limelight.getresult().isValid()) {

                        telemetry.addData("tx", limelight.getresult().getTx() +5 );
                        telemetry.addData("ty", limelight.getresult().getTy() + 7);

                        telemetry.update();

                    }

                }

            }

        }
    }
}



