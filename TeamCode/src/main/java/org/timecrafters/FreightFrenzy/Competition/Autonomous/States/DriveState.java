package org.timecrafters.FreightFrenzy.Competition.Autonomous.States;

import com.qualcomm.robotcore.hardware.DcMotor;

import org.cyberarm.engine.V2.CyberarmState;
import org.timecrafters.FreightFrenzy.Competition.Common.Robot;

public class DriveState extends CyberarmState {
    Robot robot;
    int distanceLeft, distanceRight;
    double powerLeft, powerRight;

    public DriveState(Robot robot, String groupName, String actionName) {
        this.robot = robot;
        this.distanceLeft = robot.configuration.variable(groupName, actionName, "distanceLeft").value();
        this.distanceRight = robot.configuration.variable(groupName, actionName, "distanceRight").value();
        this.powerLeft = robot.configuration.variable(groupName, actionName, "powerLeft").value();
        this.powerRight = robot.configuration.variable(groupName, actionName, "powerRight").value();
    }

    @Override
    public void start() {
        robot.driveGoalLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.driveGoalRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.driveWarehouseLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.driveWarehouseRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        robot.driveGoalLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        robot.driveGoalRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        robot.driveWarehouseLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        robot.driveWarehouseRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    @Override
    public void exec() {
        if (Math.abs(robot.driveGoalLeft.getCurrentPosition()) >= distanceLeft &&
                Math.abs(robot.driveGoalRight.getCurrentPosition()) >= distanceRight) {
            robot.driveGoalLeft.setPower(0);
            robot.driveGoalRight.setPower(0);
            robot.driveWarehouseRight.setPower(0);
            robot.driveWarehouseLeft.setPower(0);
            setHasFinished(true);
        } else {
            robot.driveGoalLeft.setPower(powerLeft);
            robot.driveGoalRight.setPower(powerRight);
            robot.driveWarehouseRight.setPower(powerRight);
            robot.driveWarehouseLeft.setPower(powerLeft);

        }


    }

    @Override
    public void telemetry() {
        engine.telemetry.addData("distance left", distanceLeft);
        engine.telemetry.addData("distance right", distanceRight);
        engine.telemetry.addData("power left", powerLeft);
        engine.telemetry.addData("power Right", powerRight);
    }
}
