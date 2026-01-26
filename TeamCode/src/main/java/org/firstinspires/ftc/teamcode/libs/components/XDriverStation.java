package org.firstinspires.ftc.teamcode.libs.components;

import com.qualcomm.robotcore.hardware.Gamepad;

public class XDriverStation {

    Gamepad gamepad_1;
    Gamepad gamepad_2;

    XGamepad xgamepad_1;
    XGamepad xgamepad_2;

    XGamepad gamepad;



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


}
