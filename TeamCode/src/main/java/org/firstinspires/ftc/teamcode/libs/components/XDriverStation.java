package org.firstinspires.ftc.teamcode.libs.components;

import com.qualcomm.robotcore.hardware.Gamepad;

public class XDriverStation{

    private  final Gamepad gamepad_1;
    private final Gamepad gamepad_2;

    private final XGamepad xgamepad_1;
    private final XGamepad xgamepad_2;

    private XGamepad master_gamepad;


    private boolean dual_player = true;

    public XDriverStation(Gamepad gamepad_1, Gamepad gamepad_2){

        this.gamepad_1 = gamepad_1;
        this.gamepad_2 = gamepad_2;

        xgamepad_1 = new XGamepad(gamepad_1);
        xgamepad_2 = new XGamepad(gamepad_2);

        xgamepad_1.update_gamepad();
        xgamepad_2.update_gamepad();

    }

    public void update(){

        xgamepad_1.update_gamepad();
        xgamepad_2.update_gamepad();

        if(xgamepad_1.getBack().is_pressed()){

            dual_player = !dual_player;

        }

    }
    
    public void update_master_gamepad() {

        master_gamepad.getDpad_up().update_state(gamepad_1.dpad_up || gamepad_2.dpad_up && dual_player);
        master_gamepad.getDpad_right().update_state(gamepad_1.dpad_down || gamepad_2.dpad_down && dual_player);
        master_gamepad.getDpad_left().update_state(gamepad_1.dpad_left || gamepad_2.dpad_left && dual_player);
        master_gamepad.getDpad_right().update_state(gamepad_1.dpad_right || gamepad_2.dpad_right && dual_player);
        master_gamepad.getA().update_state(gamepad_1.a && !gamepad_1.start || gamepad_2.a && !gamepad_2.start && dual_player);
        master_gamepad.getB().update_state(gamepad_1.b && !gamepad_1.start || gamepad_2.b && !gamepad_2.start && dual_player);
        master_gamepad.getX().update_state(gamepad_1.x || gamepad_2.x && dual_player);
        master_gamepad.getY().update_state(gamepad_1.y || gamepad_2.y && dual_player);
        master_gamepad.getGuide().update_state(gamepad_1.guide || gamepad_2.guide && dual_player);
        master_gamepad.getStart().update_state(gamepad_1.start || gamepad_2.start && dual_player);
        master_gamepad.getStart().update_state(gamepad_1.back || gamepad_2.back && dual_player);
        master_gamepad.getLeft_bumper().update_state(gamepad_1.left_bumper || gamepad_2.left_bumper && dual_player);
        master_gamepad.getRight_bumper().update_state(gamepad_1.right_bumper || gamepad_2.right_bumper && dual_player);
        master_gamepad.getLeft_stick_button().update_state(gamepad_1.left_stick_button || gamepad_2.left_stick_button && dual_player);
        master_gamepad.getRight_stick_button().update_state(gamepad_1.right_stick_button || gamepad_2.right_stick_button && dual_player);

    }
}
