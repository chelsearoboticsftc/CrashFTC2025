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
import org.firstinspires.ftc.teamcode.subsystems.example.ShooterSubsystem;

import java.util.Arrays;

@Autonomous
public class FarAutonBlue extends LinearOpMode{
    @Override
    public void runOpMode() throws InterruptedException{
        MecanumDrive drive = new MecanumDrive(hardwareMap, new Pose2d(0, 0, 0));
        ShooterSubsystem shooter = new ShooterSubsystem(hardwareMap);
        Intake intake = new Intake(hardwareMap);

        //set velocity constranits
        VelConstraint baseVelConstraint = new MinVelConstraint(Arrays.asList(
                new TranslationalVelConstraint(10.0),
                new AngularVelConstraint(Math.PI / 2)
        ));
        AccelConstraint baseAccelConstraint = new ProfileAccelConstraint(-10.0, 25.0);

        waitForStart();

            if(isStopRequested()) return;
            Actions.runBlocking(
                    drive.actionBuilder(new Pose2d(0, 0, 0))
                            .setTangent(0.0)
                            .lineToY(-76,
                                    baseVelConstraint,
                                    baseAccelConstraint)
                            .build());

        Actions.runBlocking(
                drive.actionBuilder(new Pose2d(0, 0, 0))
                        .turn(Math.toRadians(-45))
                        .build());

            shooter.prime(0, 1);
            Thread.sleep(3200);

            intake.setIntakePower(1);
            Thread.sleep(850);

            intake.setIntakePower(0);
            Thread.sleep(1000);

            intake.setIntakePower(1);
            Thread.sleep(1500);

            shooter.prime(0, 0);


        Actions.runBlocking(
                drive.actionBuilder(new Pose2d(0, 0, 0))
                        .turn(Math.toRadians(-45))
                        .build());

    }
}
