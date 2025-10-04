package org.firstinspires.ftc.teamcode.opmodes.teleop;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.teamcode.subsystems.example.ShooterSubsystem;
import com.qualcomm.robotcore.hardware.HardwareMap;

@TeleOp
public class RightBumperTest extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
     waitForStart();

     while(opModeIsActive()){

     }


         telemetry.addData("gamepad1.right_trigger", gamepad1.right_trigger);
     }
    }
