package org.firstinspires.ftc.teamcode.opmodes.teleop;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.PoseVelocity2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

<<<<<<< Updated upstream
import org.firstinspires.ftc.teamcode.MecanumDrive;
=======
import org.firstinspires.ftc.teamcode.subsystems.example.Intake;
import org.firstinspires.ftc.teamcode.subsystems.example.SampleLimelight;
import org.firstinspires.ftc.teamcode.subsystems.example.SampleVision;
import org.firstinspires.ftc.teamcode.subsystems.example.SampleLimelight;
import org.firstinspires.ftc.teamcode.subsystems.example.ShooterSubsystem;
import org.firstinspires.ftc.teamcode.MecanumDrive;
import org.firstinspires.ftc.teamcode.subsystems.example.ShooterSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.example.SmartShooter;

>>>>>>> Stashed changes

@TeleOp
public class BasicTeleopDrive extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        MecanumDrive drive = new MecanumDrive(hardwareMap, new Pose2d(0,0,0));
        SampleLimelight limelight = new SampleLimelight(hardwareMap);
        SmartShooter shooter = new SmartShooter(hardwareMap);
        Intake intake = new Intake(hardwareMap);
        ShooterSubsystem oldShooter = new ShooterSubsystem(hardwareMap);
        waitForStart();
<<<<<<< Updated upstream

        while(opModeIsActive()){

=======
        double hoodpos = 0.471;
        double ActVelocity;
        double distance;
        double velocity = 2850;
        while(opModeIsActive()){
            ActVelocity = shooter.getMotorVelocity();
            distance = limelight.getresult().getBotposeAvgDist();
           //Start of Rob's vision
           /* SampleVision GoalDistance = new SampleVision(hardwareMap);

                distance = GoalDistance.getDistanceToGoal(20);
                //20 equals BLUE team
                telemetry.addLine(String.format("range %6.1f inch",
                        distance));
                telemetry.update();
                sleep(5000);*/
            //End of Rob's vision
            if(gamepad1.a){
                velocity = velocity + 50;
                Thread.sleep(1000);
            }
            if(gamepad1.b){
                velocity = velocity - 50;
                Thread.sleep(1000);
            }
            if(gamepad1.xWasPressed()){
                shooter.shoot(distance);
            }
            if(gamepad1.xWasReleased()){
                shooter.setMotorVelocity(0);
            }
            if(gamepad2.left_bumper){
                intake.setIntakePower(1);
            }
            if(gamepad2.right_bumper){
                intake.setIntakePower(0);
            }

            if(gamepad2.dpad_up){
                while(gamepad2.dpad_up){
                    if(hoodpos < 0.4) {
                        hoodpos = 0.4;
                        oldShooter.setHoodPosition(0.3);
                    }
                }
            }
            if(gamepad2.dpad_down){
                while(gamepad2.dpad_down){

                        hoodpos = 0;
                        oldShooter.setHoodPosition(0.5);

                }
            }


>>>>>>> Stashed changes
            drive.setDrivePowers(
                    new PoseVelocity2d(
                            new Vector2d(-gamepad1.left_stick_y,
                                         -gamepad1.left_stick_x),
                            -gamepad1.right_stick_x));
<<<<<<< Updated upstream
=======

            telemetry.addData("Set Velocity", velocity);
            telemetry.addData("Distance", distance);
            telemetry.addData("Actual Velocity", ActVelocity);
            telemetry.update();
            }
>>>>>>> Stashed changes
        }
    }


