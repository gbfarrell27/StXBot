package org.firstinspires.ftc.teamcode.libs.components;

import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.libs.util.Button;

public class XGamepad {

    // Joystick position values
    private float left_stick_x;
    private float left_stick_y;
    private float right_stick_x;
    private float right_stick_y;

    //Trigger pressure values
    private float left_trigger_pressure;
    private float right_trigger_pressure;

    // Buttons
    private final Button dpad_up = new Button();
    private final Button dpad_down = new Button();
    private final Button dpad_left = new Button();
    private final Button dpad_right = new Button();

    private final Button a = new Button();
    private final Button b = new Button();
    private final Button x = new Button();
    private final Button y = new Button();

    private final Button left_bumper = new Button();
    private final Button right_bumper = new Button();

    private final Button left_stick_button = new Button();
    private final Button right_stick_button = new Button();

    private final Button guide = new Button();
    private final Button start = new Button();
    private final Button back = new Button();

    private Gamepad gamepad;

    /**
     * Default constructor
     */
    public XGamepad() {

    }

    /**
     * Initializes the XGamepad with an initial Gamepad object
     * @param gamepad FTC Gamepad object
     */
    public XGamepad(Gamepad gamepad) {

        this.gamepad = gamepad;

    }

    public void update_gamepad(){

        //updates the values of all joysticks and triggers
        left_stick_x = gamepad.left_stick_x;
        left_stick_y = gamepad.left_stick_y;
        right_stick_x = gamepad.right_stick_x;
        right_stick_y = gamepad.right_stick_y;

        left_trigger_pressure = gamepad.left_trigger;
        right_trigger_pressure = gamepad.right_trigger;

        //updates the values of all buttons
        dpad_up.update(gamepad.dpad_up);
        dpad_down.update(gamepad.dpad_down);
        dpad_left.update(gamepad.dpad_left);
        dpad_right.update(gamepad.dpad_right);

        a.update(gamepad.a);
        b.update(gamepad.b);
        x.update(gamepad.x);
        y.update(gamepad.y);

        left_bumper.update(gamepad.left_bumper);
        right_bumper.update(gamepad.right_bumper);

        left_stick_button.update(gamepad.left_stick_button);
        right_stick_button.update(gamepad.right_stick_button);

        guide.update(gamepad.guide);
        start.update(gamepad.start);
        back.update(gamepad.back);

        if(start.is_pressed()){

            a.update(false);

        }

    }

    public float getLeft_stick_x() {

        return left_stick_x;

    }

    public float getLeft_stick_y() {

        return left_stick_y;

    }

    public float getRight_stick_x() {

        return right_stick_x;

    }

    public float getRight_stick_y() {

        return right_stick_y;

    }

    public float getLeft_trigger_pressure() {

        return left_trigger_pressure;

    }

    public float getRight_trigger_pressure() {

        return right_trigger_pressure;

    }

    public Button getDpad_up() {

        return dpad_up;

    }

    public Button getDpad_down() {

        return dpad_down;

    }

    public Button getDpad_left() {

        return dpad_left;

    }

    public Button getDpad_right() {

        return dpad_right;

    }

    public Button getA() {

        return a;

    }

    public Button getB() {

        return b;

    }

    public Button getX() {

        return x;

    }


    public Button getY() {

        return y;

    }

    public Button getLeft_bumper() {

        return left_bumper;

    }

    public Button getRight_bumper() {

        return right_bumper;

    }

    public Button getLeft_stick_button() {

        return left_stick_button;

    }

    public Button getRight_stick_button() {

        return right_stick_button;

    }

    public Button getGuide() {

        return guide;

    }

    public Button getStart() {

        return start;

    }

    public Button getBack() {

        return back;

    }


}
