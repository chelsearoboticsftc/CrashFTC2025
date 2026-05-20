package org.firstinspires.ftc.teamcode.opmodes.auton;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.MecanumDrive;
import org.firstinspires.ftc.teamcode.subsystems.example.Intake;
import org.firstinspires.ftc.teamcode.subsystems.example.ShooterSubsystem;

@Autonomous
public class CloseAutoRedNewMay2026 extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
        MecanumDrive drive = new MecanumDrive(hardwareMap, new Pose2d(0, 0, 0));
        ShooterSubsystem shooter = new ShooterSubsystem(hardwareMap);
        Intake intake = new Intake(hardwareMap);

        shooter.prime(0.5, 0.65);

        waitForStart();


        //This sets the pose 2D and moves back 30" to get a better shot
Actions.runBlocking(
        drive.actionBuilder(new Pose2d(0,0,0))
                .lineToX(-30)
                .build()
);

        //This is the code that shoots the balls (HOLY TUFF)
        Thread.sleep(1000);
        intake.setPopUpPos(0.5);
        sleep(500);
        intake.setPopUpPos(0.12);
        sleep(500);

        intake.setIntakePower(1);
        Thread.sleep(1000);
        intake.setIntakePower(0);
        intake.setPopUpPos(0.5);
        sleep(500);
        intake.setPopUpPos(0.12);
        sleep(500);

        intake.setIntakePower(1);
        Thread.sleep(1000);
        intake.setIntakePower(0);
        intake.setPopUpPos(0.5);
        sleep(500);
        intake.setPopUpPos(0.12);



    }
}
