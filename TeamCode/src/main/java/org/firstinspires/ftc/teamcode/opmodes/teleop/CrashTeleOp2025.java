package org.firstinspires.ftc.teamcode.opmodes.teleop;
import static org.firstinspires.ftc.teamcode.subsystems.example.PersistentData.AutoEndPose;

import com.acmerobotics.roadrunner.MecanumKinematics;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.PoseVelocity2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.subsystems.example.Intake;
import org.firstinspires.ftc.teamcode.subsystems.example.PersistentData;
import org.firstinspires.ftc.teamcode.subsystems.example.SampleLimelight;
import org.firstinspires.ftc.teamcode.subsystems.example.SampleVision;
import org.firstinspires.ftc.teamcode.subsystems.example.ShooterSubsystem;
import org.firstinspires.ftc.teamcode.MecanumDrive;
import org.firstinspires.ftc.teamcode.subsystems.example.ShooterSubsystem;

//Vaughn
@TeleOp
@Disabled
public class CrashTeleOp2025 extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
        MecanumDrive drive = new MecanumDrive(hardwareMap, new Pose2d(0, 0, 0));
        Intake intake = new Intake(hardwareMap);
        ShooterSubsystem shooter = new ShooterSubsystem(hardwareMap);
        SampleLimelight limelight = new SampleLimelight(hardwareMap);

        waitForStart();
double start;
double ET;
        telemetry.addData("Vaughn Value", AutoEndPose);
        telemetry.update();

        while(opModeIsActive()){

            drive.setDrivePowers(
                    new PoseVelocity2d(
                            new Vector2d(-gamepad1.left_stick_y,
                                    -gamepad1.left_stick_x),
                            -gamepad1.right_stick_x));
            //intake
            if(gamepad2.xWasPressed()){
                intake.setIntakePower(1);
            }
            if(gamepad2.xWasReleased()){
                intake.setIntakePower(0);
            }


            if(gamepad2.b){
                intake.setIntakePower(-1);
            }
            if(gamepad2.bWasReleased()){
                intake.setIntakePower(0);
            }

            //prime
            if(gamepad2.yWasPressed()) {
                shooter.runShooter(0.9);
            }
            if(gamepad2.yWasReleased()) {
                shooter.runShooter(0.3);
            }
            if(gamepad2.aWasPressed()) {
                shooter.runShooter(0.8);
            }
            if(gamepad2.aWasReleased()) {
                shooter.runShooter(0.3);
            }



            if(gamepad2.dpad_down){
                intake.setPopUpPos(0);
            }
            if(gamepad2.dpad_up){
                intake.setPopUpPos(180);
            }

            //hood
            if(gamepad1.dpad_down){
                shooter.moveHood(0);
            }
            if(gamepad1.dpad_up){
                shooter.moveHood(180);
            }

            //turret
            if(gamepad2.left_bumper){
                shooter.aim(-.5);
            }else if(gamepad2.right_bumper){
                shooter.aim(.5);
            }else{
                shooter.aim(-0);
            }
            if(limelight.getresult() != null){
                if(limelight.getresult().isValid()){

                    telemetry.addData("tx",limelight.getresult().getTx());
                    telemetry.addData("ty", limelight.getresult().getTy());

                    telemetry.update();

                }

            }

            if (gamepad2.back){
                start = getRuntime();
                ET=0;
                telemetry.addData("starting", ET);
                telemetry.update();
                gamepad2.rumble(1000);
                while (Math.abs(limelight.getresult().getTx()) > 0.5 ){
                    telemetry.addData("ET", ET);
                    shooter.aim(limelight.getresult().getTx() * 0.02);
                    ET = getRuntime() - start;
                }
                shooter.aim(0);

//                //if (limelight.getresult().getTx() < -0.1) {
//                    while (limelight.getresult().getTx() < -0.1) {
//                        shooter.aim(-0.2);
//                    }
//                    shooter.aim(0);


//                }
//                if (limelight.getresult().getTx() > 0.1) {
//                    while (limelight.getresult().getTx() > 0.1) {
//                        shooter.aim(0.2);
//                    }
//                    shooter.aim(0);


//                }
            }






        }
    }
}
