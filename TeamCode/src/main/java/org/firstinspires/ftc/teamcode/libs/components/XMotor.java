package robotx.stx_libraries.components;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.libs.templates.XOpMode;
import org.firstinspires.ftc.teamcode.libs.util.Scheduler;

public class XMotor {
    // Hardware
    private final XOpMode op;
    private final String motor_name;
    private DcMotorEx motor;

    private final double ticks_per_revolution;
    private final double max_velocity;

    private int targetPosition = 0;
    private String current_encoder_mode = "without_encoder";

    private XMotor following;

    public XMotor(XOpMode op, String motor_name, double ticks_per_revolution, double max_velocity) {

        this.op = op;
        this.motor_name = motor_name;
        this.ticks_per_revolution = ticks_per_revolution;
        this.max_velocity = max_velocity;

    }

    @Override
    public void init() {

        motor = op.hardwareMap.get(DcMotorEx.class, motor_name);

    }

    public void set_encoder_mode(String mode) {

        if(mode.contains("use_encoder")) {

            motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        } else if(mode.contains("without_encoder")) {

            motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        }

        current_encoder_mode = mode.equals("use_encoder") ? "use_encoder" : "without_encoder";

    }

    public void set_brakes(boolean brakes) {

        if(brakes) {

            motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        } else {

            motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);

        }

    }

    public void set_direction(String direction) {

        if(direction.contains("forward")) {

            motor.setDirection(DcMotorSimple.Direction.FORWARD);

        } else if(direction.contains("reverse")) {

            motor.setDirection(DcMotorSimple.Direction.REVERSE);

        }

    }

    @Override
    public void loop() {

    }


    public void setPower(double new_power) {

        double power = Range.clip(new_power, -1.0, 1.0);

        motor.setPower(power);

    }

    public double getPower() {

        return motor.getPower();

    }


    public void set_RPM(double targetRPM) {

        double target_ticks = (targetRPM / 60.0) * ticks_per_revolution;

        motor.setVelocity(target_ticks);

    }

    public void set_PIDF_coefficients(double p, double i, double d, double f) {

        motor.setVelocityPIDFCoefficients(p, i , d, f);

    }

    public void stop() {

        setPower(0);

    }

    public int getPosition() {

        return motor.getCurrentPosition();

    }

    public void setTargetPosition(int targetTicks, double power) {

        this.targetPosition = targetTicks;

        motor.setTargetPosition(targetTicks);

        motor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        motor.setPower(power);

    }

    public void incrementTargetPosition(int ticks, double power) {

        setTargetPosition(targetPosition + ticks, power);

    }

    public boolean atTarget(int toleranceTicks) {
        return Math.abs(motor.getCurrentPosition() - targetPosition) < toleranceTicks;
    }

    public boolean isBusy() {
        return motor.isBusy();
    }

    public void resetEncoder() {
        DcMotor.RunMode previousMode = motor.getMode();
        motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor.setMode(previousMode);
    }

    public void setInverted(boolean inverted) {
        motor.setDirection(inverted ? DcMotorSimple.Direction.REVERSE : DcMotorSimple.Direction.FORWARD);
    }

    public void setZeroPowerBehavior(DcMotor.ZeroPowerBehavior behavior) {
        motor.setZeroPowerBehavior(behavior);
    }


    public void setTicksPerRevolution(double ticks) {
        this.ticksPerRevolution = ticks;
    }

    public void follow(XMotor master) {
        this.following = master;
    }

    public void stopFollowing() {
        this.following = null;
    }

    public void enableStallProtection(boolean enable) {
        this.isStallProtectionEnabled = enable;
        stallTimer.reset();
    }

    private void checkStall() {
        // If we are commanding power, but velocity is near zero
        if (Math.abs(getPower()) > 0.1 && Math.abs(getVelocityRPM()) < stallThresholdRPM) {
            // If the timer hasn't started, start it (conceptually, Stopwatch usually runs continuously, 
            // so we might need a specific check for duration)
            if (stallTimer.elapsedMillis() > 1000) { // 1 second stall
                op.telemetry.addData("WARNING", "Motor Stalled: " + motor_name);
                setPower(0);
                disable(); // Custom disable flag or just stop
            }
        } else {
            stallTimer.reset(); // Reset timer if we are moving or not powered
        }
    }

    public void runForTime(Scheduler scheduler, int millis, double power) {
        setPower(power);
        scheduler.schedule(millis, motor_name + "_timer", this::stop);
    }

    public DcMotorEx getInternalMotor() {
        return motor;
    }

}
