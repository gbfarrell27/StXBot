package org.firstinspires.ftc.teamcode.libs.templates;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.libs.components.XDriverStation;
import org.firstinspires.ftc.teamcode.libs.components.XMotor;
import org.firstinspires.ftc.teamcode.libs.util.Scheduler;

public abstract class XOpMode extends OpMode {

    private final XRobotContext context = new XRobotContext(this);

    private final XDriverStation driver_station =  new XDriverStation(gamepad1, gamepad2);

    private final Scheduler scheduler = new Scheduler();

    public void initModules(){


    }

    @Override
    public void init() {

        initModules();

        for(XSystem system : context.getActive_systems()){

            system.init();

        }

        for(XSystem system : context.getInactive_systems()){

            system.init();

        }

    }

    @Override
    public void init_loop() {

        scheduler.loop();

        driver_station.update();

        for (XSystem system : context.getActive_systems()) {

            system.init_loop();

        }

    }

    @Override
    public void start() {

        for(XSystem system : context.getActive_systems()){

            system.start();

        }

    }
    @Override
    public void loop() {

        scheduler.loop();

        driver_station.update();

    }

    @Override
    public void stop() {

        for(XSystem system : context.getActive_systems()){

            system.stop();

        }

    }

    public void register_module(XSystem module, XRobotContext.ModuleType type){

        context.register_module(module, type);

    }

    public XRobotContext getContext(){

        return context;

    }

    public XDriverStation getDriver_station(){

        return driver_station;

    }

    public Scheduler getScheduler() {

        return scheduler;

    }

}
