package org.firstinspires.ftc.teamcode.opmodes.auton;

import com.acmerobotics.roadrunner.AccelConstraint;
import com.acmerobotics.roadrunner.AngularVelConstraint;
import com.acmerobotics.roadrunner.MinVelConstraint;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.ProfileAccelConstraint;
import com.acmerobotics.roadrunner.TranslationalVelConstraint;
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
public class FarAutonBlue extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        SampleLimelight limelight = new SampleLimelight(hardwareMap);
        MecanumDrive drive = new MecanumDrive(hardwareMap, new Pose2d(0, 0, 0));
        ShooterSubsystem shooter = new ShooterSubsystem(hardwareMap);
        Intake intake = new Intake(hardwareMap);

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
                        .lineToX(-9,
                                baseVelConstraint,
                                baseAccelConstraint)
                        .build());
        if (limelight.getresult() != null) {
            if (limelight.getresult().isValid()) {

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
            }


            shooter.prime(0, 0.85);
            Thread.sleep(3000);

            intake.setIntakePower(1);
            Thread.sleep(650);

            intake.setIntakePower(0);
            Thread.sleep(2700);

            intake.setIntakePower(1);
            Thread.sleep(525);

            intake.setIntakePower(0);
            Thread.sleep(2700);

            intake.setIntakePower(1);
            Thread.sleep(1550);

            shooter.prime(0, 0);


            Actions.runBlocking(
                            drive.actionBuilder(new Pose2d(0, 0, 0))
                                    .turn(Math.toRadians(-90))
                                .lineToY(10,
                                    baseVelConstraint,
                                    baseAccelConstraint)
                                            .build());
                    intake.setIntakePower(1);
            Actions.runBlocking(
                    drive.actionBuilder(new Pose2d(0, 0 , 90))
                            .lineToY(24)
                            .build());

            intake.setIntakePower(0);

        }
    }
}
