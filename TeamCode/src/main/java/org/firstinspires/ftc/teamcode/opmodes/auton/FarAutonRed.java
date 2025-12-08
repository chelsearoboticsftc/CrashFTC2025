package org.firstinspires.ftc.teamcode.opmodes.auton;

import static org.firstinspires.ftc.teamcode.subsystems.example.PersistentData.AutoEndPose;

import com.acmerobotics.roadrunner.AccelConstraint;
import com.acmerobotics.roadrunner.AngularVelConstraint;
import com.acmerobotics.roadrunner.MinVelConstraint;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.ProfileAccelConstraint;
import com.acmerobotics.roadrunner.TranslationalVelConstraint;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.VelConstraint;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.MecanumDrive;
import org.firstinspires.ftc.teamcode.subsystems.example.Intake;
import org.firstinspires.ftc.teamcode.subsystems.example.SampleLimelight;
import org.firstinspires.ftc.teamcode.subsystems.example.ShooterSubsystem;

import java.util.Arrays;

@Autonomous
public class FarAutonRed extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        SampleLimelight limelight = new SampleLimelight(hardwareMap);
        MecanumDrive drive = new MecanumDrive(hardwareMap, new Pose2d(0, 0, 0));
        ShooterSubsystem shooter = new ShooterSubsystem(hardwareMap);
        Intake intake = new Intake(hardwareMap);
        drive.localizer.setPose(new Pose2d(0, 0 , 90));
        //set velocity constraints
        VelConstraint baseVelConstraint = new MinVelConstraint(Arrays.asList(
                new TranslationalVelConstraint(30.0),
                new AngularVelConstraint(Math.PI / 2)
        ));
        AccelConstraint baseAccelConstraint = new ProfileAccelConstraint(-10.0, 30.0);

        waitForStart();

        if (isStopRequested()) return;
        Actions.runBlocking(
                drive.actionBuilder(new Pose2d(0, 0, 0))
                        //.setTangent(180.0)
                        .lineToX(0.1,
                                baseVelConstraint,
                                baseAccelConstraint)
                        .build());

                 shooter.prime(0, 0.89);
                //telemetry.addData("Pose2d that the limelight gives", botpose);
                telemetry.addData("tx", limelight.getresult().getTx());
                telemetry.addData("ty", limelight.getresult().getTy());
                //telemetry.addData("pos",botpose.position);
                //telemetry.addData("heading",botpose.heading);
                telemetry.update();

                if (limelight.getresult().getTx() < -0.1) {
                    while (limelight.getresult().getTx() < -0.1) {
                        shooter.aim(-0.2);
                    }
                    shooter.aim(0);


                }
                if (limelight.getresult().getTx() > 0.1) {
                    while (limelight.getresult().getTx() > 0.1) {
                        shooter.aim(0.2);
                    }
                    shooter.aim(0);


                }





                shooter.moveHood(0.3);
            Thread.sleep(2100);

        Thread.sleep(1400);

        intake.setPopUpPos(0.5);
        sleep(650);
        intake.setPopUpPos(0.12);
        intake.setIntakePower(1);
        Thread.sleep(650);

        intake.setIntakePower(0);
        Thread.sleep(1450);

        intake.setPopUpPos(0.5);
        sleep(500);
        intake.setPopUpPos(0.12);
        intake.setIntakePower(1);
        Thread.sleep(525);

        intake.setIntakePower(0);
        Thread.sleep(1450);

        intake.setPopUpPos(0.5);
        sleep(500);
        intake.setPopUpPos(0.12);
        intake.setIntakePower(1);
        Thread.sleep(1550);

            shooter.prime(0, 0);


            Actions.runBlocking(
                            drive.actionBuilder(new Pose2d(0, 0, 0))
                                    .lineToX(22)
                                    .turn(Math.toRadians(-83))
                                //.lineToY(20,
                                 //   baseVelConstraint,
                                  //  baseAccelConstraint)
                                    .build());
                   // intake.setIntakePower(1);
            Actions.runBlocking(
                    drive.actionBuilder(new Pose2d(22, 0 , Math.toRadians(-83)))
                            .lineToY(-40)
                            .build());
            intake.setIntakePower(0);
            /*Actions.runBlocking(
                    drive.actionBuilder(new Pose2d(0, 0 , 90))
                            .lineToY(20)
                            .build());*/
        Actions.runBlocking(
                drive.actionBuilder(new Pose2d(22, -40, Math.toRadians(-83)))
                        .strafeTo(new Vector2d(0, 0))
                        //.lineToY(20,
                        //   baseVelConstraint,
                        //  baseAccelConstraint)
                        .build());
        // intake.setIntakePower(1);
        shooter.setMotorPower(.95);
        if(!limelight.getresult().isValid()){
            while(!limelight.getresult().isValid()){
                shooter.aim(-0.4);
            }
        }
        shooter.aim(0);
        /*shooter.aim(-0.4);
        Thread.sleep(600);
        shooter.aim(0);*/
        if (limelight.getresult().getTx() < -0.1) {
            while (limelight.getresult().getTx() < -0.1) {
                shooter.aim(-0.2);
            }
            shooter.aim(0);


        }
        if (limelight.getresult().getTx() > 0.1) {
            while (limelight.getresult().getTx() > 0.1) {
                shooter.aim(0.2);
            }
            shooter.aim(0);


        }

        Thread.sleep(1400);

        intake.setPopUpPos(0.5);
        sleep(650);
        intake.setPopUpPos(0.12);
        intake.setIntakePower(1);
        Thread.sleep(650);

        intake.setIntakePower(0);
        Thread.sleep(1850);

        intake.setPopUpPos(0.5);
        sleep(500);
        intake.setPopUpPos(0.12);
        intake.setIntakePower(1);
        Thread.sleep(525);

        intake.setIntakePower(0);
        //Thread.sleep(2050);

       // intake.setPopUpPos(0.5);
       // sleep(500);
        // intake.setPopUpPos(0.12);
      //  intake.setIntakePower(1);
       // Thread.sleep(1550);

        shooter.prime(0, 0);
        Actions.runBlocking(
                drive.actionBuilder(new Pose2d(0, 0, Math.toRadians(-83)))
                        .lineToY(-10)
                        .build()
        );

        Actions.runBlocking(
                drive.actionBuilder(new Pose2d(0, -10, Math.toRadians(-83)))
                        .build()
        );

        AutoEndPose = drive.localizer.getPose();
        telemetry.addData("Vaughn Value", AutoEndPose);
        telemetry.update();
        }
    }

