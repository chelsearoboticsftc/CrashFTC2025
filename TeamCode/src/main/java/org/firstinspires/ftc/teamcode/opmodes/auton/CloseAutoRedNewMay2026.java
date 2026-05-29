//All of the files/packages that allow the things to work.

package org.firstinspires.ftc.teamcode.opmodes.auton;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;
import static org.firstinspires.ftc.teamcode.subsystems.example.PersistentData.AutoEndPose;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.MecanumDrive;
import org.firstinspires.ftc.teamcode.subsystems.example.Intake;
import org.firstinspires.ftc.teamcode.subsystems.example.SampleLimelight;
import org.firstinspires.ftc.teamcode.subsystems.example.ShooterSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.example.SmartShooter;

@Autonomous
public class CloseAutoRedNewMay2026 extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
        MecanumDrive drive = new MecanumDrive(hardwareMap, new Pose2d(0, 0, 0));
        ShooterSubsystem shooter = new ShooterSubsystem(hardwareMap);
        Intake intake = new Intake(hardwareMap);
        SampleLimelight limelight = new SampleLimelight(hardwareMap);
        SmartShooter smartShooter = new SmartShooter(hardwareMap);
        drive.localizer.setPose(new Pose2d(0, 0 , 45));
        shooter.prime(0.325, 0.70);
        double ET;
        double start;

        waitForStart();


        //This sets the pose 2D and moves back 30" to get a better shot
Actions.runBlocking(
        drive.actionBuilder(new Pose2d(0,0,45))
                .lineToX(-25)
                .build()
);

        //This is the code that shoots the balls (HOLY TUFF)
        Thread.sleep(500);
        smartShooter.shoot((limelight.getresult().getBotposeAvgDist()));
        sleep(1500);
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
        sleep(200);
        intake.setIntakePower(1);

        //Code that """"""CAN"""""" pick up more balls.


        Actions.runBlocking(
                drive.actionBuilder(new Pose2d(-25,0, Math.toRadians(45)))
                        .turnTo(Math.toRadians(5))
                        .lineToX(0)
                        .build());

                sleep(250);

                Actions.runBlocking(
                        drive.actionBuilder(new Pose2d(0,0, Math.toRadians(0)))
                                .lineToX(-25)
                                .turnTo(Math.toRadians(50))
                                .build()

        );
        //This is the code that shoots the balls AGAIN (HOLY TUFF SQUARED)
        start = getRuntime();
        ET = 0;
        while (Math.abs(limelight.getresult().getTx()) > 0.5 && ET< 1) {
//                            telemetry.addData("ET", ET);
            shooter.aim(-limelight.getresult().getTx() * 0.02);
            ET = getRuntime() - start;
        }
        sleep(500);
        shooter.aim(0);
        intake.setIntakePower(0);
        Thread.sleep(250);
        intake.setPopUpPos(0.5);
        sleep(500);
        intake.setPopUpPos(0.12);
        sleep(750);

        intake.setIntakePower(1);
        Thread.sleep(500);
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
        sleep(200);
        intake.setIntakePower(1);

        //Takes The Pose For RRfieldCentricDrive
        AutoEndPose = drive.localizer.getPose();
        telemetry.addData("Vaughn Value", AutoEndPose);
        telemetry.update();

    }
}
