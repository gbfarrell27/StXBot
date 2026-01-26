package org.firstinspires.ftc.teamcode.libs.util;

public class Stopwatch {

    private long start_time;

    private long target_time;

    public Stopwatch(long millis){

        start_timer(millis);

    }

    public void reset(){

        start_time = System.nanoTime();

    }

    public void clear(){

        target_time = 0;

    }

    public long elapsed_nano_time(){

        return System.nanoTime() - start_time;

    }

    public long remaining_nano_time(){

        return Math.max(0, target_time - elapsed_nano_time());

    }

    public void start_timer(long millis){

        reset();

        target_time = millis * 1_000_000L;

    }

    public boolean is_timer_done(){

        return is_timer_running() && remaining_nano_time() == 0;

    }

    public boolean is_timer_running(){

        return target_time != 0 && remaining_nano_time() > 0;

    }

}
