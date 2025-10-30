package org.firstinspires.ftc.teamcode.opmodes.teleop;
import com.acmerobotics.roadrunner.MecanumKinematics;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.PoseVelocity2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.subsystems.example.Intake;
import org.firstinspires.ftc.teamcode.subsystems.example.SampleVision;
import org.firstinspires.ftc.teamcode.subsystems.example.ShooterSubsystem;
import org.firstinspires.ftc.teamcode.MecanumDrive;
import org.firstinspires.ftc.teamcode.subsystems.example.ShooterSubsystem;

//Vaughn
@TeleOp
public class CrashTeleOp2025 extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        MecanumDrive drive = new MecanumDrive(hardwareMap, new Pose2d(0, 0, 0));
        Intake intake = new Intake(hardwareMap);
        ShooterSubsystem shooter = new ShooterSubsystem(hardwareMap);
        waitForStart();
        while(opModeIsActive()){

            drive.setDrivePowers(
                    new PoseVelocity2d(
                            new Vector2d(-gamepad1.left_stick_y,
                                    -gamepad1.left_stick_x),
                            -gamepad1.right_stick_x));
            //intake
            if(gamepad2.x){
                intake.setIntakePower(0);
            }
            if(gamepad2.xWasReleased()){
                intake.setIntakePower(-1);
            }


            if(gamepad2.b){
                intake.setIntakePower(-1);
            }
            if(gamepad2.bWasReleased()){
                intake.setIntakePower(0);
            }

            //prime
            if(gamepad2.yWasPressed()) {
                shooter.runShooter(-1);
            }
            if(gamepad2.yWasReleased()) {
                shooter.runShooter(0);
            }

            //fire
            if(gamepad2.dpad_down){
                intake.setPopUpPos(0);
            }
            if(gamepad2.dpad_up){
                intake.setPopUpPos(-0.5);
            }



        }
    }
}
