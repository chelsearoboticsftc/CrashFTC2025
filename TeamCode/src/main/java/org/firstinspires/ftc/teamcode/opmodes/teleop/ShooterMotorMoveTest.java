package org.firstinspires.ftc.teamcode.opmodes.teleop;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.teamcode.subsystems.example.ShooterSubsystem;
import com.qualcomm.robotcore.hardware.HardwareMap;


@TeleOp
public class ShooterMotorMoveTest extends LinearOpMode {


    @Override
    public void runOpMode() throws InterruptedException {
        waitForStart();

        while (opModeIsActive()) {
            ShooterSubsystem Shooter = new ShooterSubsystem(hardwareMap);


            if (gamepad1.a) {
                Shooter.runTopShooterMotor(1);

            } else if (gamepad1.b) {
                Shooter.runBottomShooterMotor(1);
            } else if (gamepad1.y) {
                Shooter.runShooter(1);
            }else{
                Shooter.runShooter(0);
            }


        }

    }
}