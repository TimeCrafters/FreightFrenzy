package org.timecrafters.FreightFrenzy.Competition.Autonomous.Engines;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.cyberarm.engine.V2.CyberarmEngine;
import org.timecrafters.FreightFrenzy.Competition.Autonomous.States.CollectorToggle;
import org.timecrafters.FreightFrenzy.Competition.Autonomous.States.DriveState;
import org.timecrafters.FreightFrenzy.Competition.Autonomous.States.TensorFlowState;
import org.timecrafters.FreightFrenzy.Competition.Autonomous.States.TurretArmExtension;
import org.timecrafters.FreightFrenzy.Competition.Autonomous.States.TurretArmRiser;
import org.timecrafters.FreightFrenzy.Competition.Autonomous.States.TurretOrbit;
import org.timecrafters.FreightFrenzy.Competition.Common.Robot;

@Autonomous (name = "Blue WareHouse", group = "blue")
public class BlueWarehouseEngine extends CyberarmEngine {
    Robot robot;

    @Override
    public void setup() {
        this.robot = new Robot(this);
        robot.resetEncoders();

        addState(new TurretOrbit(robot, robot.turretServoOrange, robot.orangeMag, "BlueWarehouseAutonomous", "01_0"));
        addState(new TensorFlowState(robot, robot.orangeArmRiser, robot.orangeArmBobbin, "BlueWarehouseAutonomous", "01_1"));
//        addState(new TurretArmExtension(robot, robot.orangeArmBobbin, "RedWarehouseAutonomous", "02_0"));
//        addState(new TurretArmRiser(robot, robot.orangeArmRiser, "RedWarehouseAutonomous", "03_0_middle"));
//        addState(new TurretArmExtension(robot, robot.orangeArmBobbin, "RedWarehouseAutonomous", "04_0"));
        addState(new CollectorToggle(robot, robot.collectorOrange, "BlueWarehouseAutonomous", "05_0"));
        addState(new CollectorToggle(robot, robot.collectorOrange, "BlueWarehouseAutonomous", "06_0"));
        addState(new TurretArmExtension(robot, robot.orangeArmBobbin, "BlueWarehouseAutonomous", "07_0"));
        addState(new DriveState(robot,"BlueWarehouseAutonomous", "08_0"));
        addState(new DriveState(robot, "BlueWarehouseAutonomous", "09_0"));
        addState(new TurretOrbit(robot, robot.turretServoOrange, null, "BlueWarehouseAutonomous", "10_0"));
        addState(new DriveState(robot, "BlueWarehouseAutonomous", "10_1"));

    }

    public void loop() {
        super.loop();

        telemetry.addData("white arm extension", robot.whiteArmBobbin.getCurrentPosition());
        telemetry.addData("White Riser Arm", robot.whiteArmRiser.getCurrentPosition());
        telemetry.addData("white Turret Switch", robot.whiteMag.isPressed());
        telemetry.addData("white Turret orbit Power", robot.turretServoWhite.getPower());
        telemetry.addData("White Door Position", robot.whiteDispenser.getPosition());
        telemetry.addLine();

        telemetry.addData("Orange Riser Arm", robot.orangeArmRiser.getCurrentPosition());
        telemetry.addData("orange arm extension", robot.orangeArmBobbin.getCurrentPosition());
        telemetry.addData("orange Turret Switch", robot.orangeMag.isPressed());
        telemetry.addData("orange Turret Orbit Power", robot.turretServoOrange.getPower());
        telemetry.addData("Orange Door Position", robot.orangeDispenser.getPosition());
        telemetry.addLine();

        telemetry.addData("driveWarehouseLeft", robot.driveWarehouseLeft.getCurrentPosition());
        telemetry.addData("driveWarehouseRight", robot.driveWarehouseRight.getCurrentPosition());
        telemetry.addData("driveGoalLeft", robot.driveGoalLeft.getCurrentPosition());
        telemetry.addData("driveGoalRight", robot.driveGoalRight.getCurrentPosition());
    }
}
