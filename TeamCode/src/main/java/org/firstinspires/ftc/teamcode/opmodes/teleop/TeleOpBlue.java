package org.firstinspires.ftc.teamcode.opmodes.teleop;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp
@Disabled
public class TeleOpBlue extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException{
        RRfieldCentricDrive teleOp = new RRfieldCentricDrive();

        waitForStart();
        teleOp.setTagID(20);
        teleOp.runOpMode();
    }
}
