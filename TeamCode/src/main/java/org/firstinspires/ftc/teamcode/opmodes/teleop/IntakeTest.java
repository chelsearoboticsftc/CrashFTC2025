package org.firstinspires.ftc.teamcode.opmodes.teleop;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.PoseVelocity2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.subsystems.example.Intake;
import org.firstinspires.ftc.teamcode.subsystems.example.SampleVision;
import org.firstinspires.ftc.teamcode.subsystems.example.ShooterSubsystem;
import org.firstinspires.ftc.teamcode.MecanumDrive;
import org.firstinspires.ftc.teamcode.subsystems.example.ShooterSubsystem;

@TeleOp
public class IntakeTest extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException{

        waitForStart();
        Intake intake = new Intake(hardwareMap);
        while(opModeIsActive()){
            if(gamepad1.b) {
                intake.setIntakePower(gamepad1.right_stick_y);
            }else{
                intake.setIntakePower(0);
            }
        }
    }
}
