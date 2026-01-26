package org.firstinspires.ftc.teamcode.libs.util;

import java.util.concurrent.TimeUnit;

public class Stopwatch {

    private long start_time;

    private long target_time;

    private int reset_count = 0;


    public Stopwatch(){


    }

    public Stopwatch(long millis){

        start_timer(millis);

    }

    public void reset(){

        start_time = System.nanoTime();
        reset_count++;

    }

    public void clear(){

        target_time = 0;

    }

    public long elapsed_nano_time(){

        return System.nanoTime() - start_time;

    }

    public long elapsed_milli_time(){

        return elapsed_nano_time() / 1_000_000L;

    }

    public long remaining_nano_time(){

        return Math.max(0, target_time * 1_000_000L - elapsed_nano_time());

    }
    public long remaining_milli_time(){

        return remaining_nano_time() / 1_000_000L;

    }

    public void start_timer(long millis){

        reset();

        target_time = millis;

    }
    public boolean is_timer_done(){

        return target_time != 0 && !(remaining_milli_time() >= 0);

    }

}
