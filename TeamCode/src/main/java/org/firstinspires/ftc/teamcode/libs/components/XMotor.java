package org.firstinspires.ftc.teamcode.libs.components;

import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.firstinspires.ftc.teamcode.libs.templates.XOpMode;

public class XMotor {

    private final XOpMode op;

    private final String motor_name;

    private DcMotorEx motor;


    public void setRPM(double target){
        motor.setVelocity(rpm * 360 / 60);






}

