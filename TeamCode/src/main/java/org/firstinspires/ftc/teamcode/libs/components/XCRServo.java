package org.firstinspires.ftc.teamcode.libs.components;

import org.firstinspires.ftc.teamcode.libs.templates.XOpMode;

import com.qualcomm.robotcore.hardware.CRServoImplEx;

public class XCRServo {

    private final XOpMode op;

    private final String servo_name;

    private CRServoImplEx CR_servo;

    private double power;

    public XCRServo(XOpMode op, String servo_name) {

        this.op = op;

        this.servo_name = servo_name;

    }

    public void init(){

        this.CR_servo = op.hardwareMap.get(CRServoImplEx.class, servo_name);

        power = 0.0;

    }

    public void rotate(double power){

        this.power = power;

        CR_servo.setPower(power);

    }

    public void stop(){

        this.power = 0.0;

        CR_servo.setPower(0.0);

    }

}
