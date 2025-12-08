package org.firstinspires.ftc.teamcode.opmodes.auton;


import static org.firstinspires.ftc.teamcode.subsystems.example.PersistentData.AutoEndPose;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.MecanumDrive;
import org.firstinspires.ftc.teamcode.subsystems.example.Intake;
import org.firstinspires.ftc.teamcode.subsystems.example.PersistentData;
import org.firstinspires.ftc.teamcode.subsystems.example.ShooterSubsystem;

@Autonomous
@Disabled
public class CompromiseAutonRed extends LinearOpMode{
    @Override
    public void runOpMode() throws InterruptedException{
        MecanumDrive drive = new MecanumDrive(hardwareMap, new Pose2d(0, 0, 0));
        ShooterSubsystem shooter = new ShooterSubsystem(hardwareMap);
        Intake intake = new Intake(hardwareMap);

        waitForStart();
            telemetry.addData("Vaughn Value", AutoEndPose);
            telemetry.update();

            if(isStopRequested()) return;
            Actions.runBlocking(
                    drive.actionBuilder(new Pose2d(0, 0, 0))
                            .lineToX(10)
                            .turn(Math.toRadians(77.4))
                            .build());
            /*shooter.prime(0, -1);
            Thread.sleep(2500);
            intake.setIntakePower(1);
            Thread.sleep(700);
            shooter.prime(0, -1);*/
        AutoEndPose = drive.localizer.getPose();
        telemetry.addData("Vaughn Value", AutoEndPose);
        telemetry.update();
    }
}
