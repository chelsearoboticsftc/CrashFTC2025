package org.firstinspires.ftc.teamcode.opmodes.auton;

import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.acmerobotics.roadrunner.Pose2d;

import org.firstinspires.ftc.teamcode.subsystems.example.Intake;
import org.firstinspires.ftc.teamcode.subsystems.example.ShooterSubsystem;
import org.firstinspires.ftc.teamcode.MecanumDrive;

@Autonomous
public class GoalAutonRed extends LinearOpMode{
    @Override
    public void runOpMode() throws InterruptedException{
        MecanumDrive drive = new MecanumDrive(hardwareMap, new Pose2d(0, 0, 0));
        ShooterSubsystem shooter = new ShooterSubsystem(hardwareMap);
        Intake intake = new Intake(hardwareMap);

        waitForStart();

            if(isStopRequested()) return;
            Actions.runBlocking(
                    drive.actionBuilder(new Pose2d(0, 0, 0))
                            .lineToX(55)
                            .build());
            shooter.prime(0, 1);
            Thread.sleep(3200);
            intake.setIntakePower(1);
            Thread.sleep(850);
            shooter.prime(0, 1);


    }
}
