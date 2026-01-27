package org.firstinspires.ftc.teamcode.libs.tuning;

import android.content.Context;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class PIDFTuner {

    private double kP;
    private double kI;
    private double kD;
    private double kF;

    private double I;

    private double last_error;

    private double delta_time;

    private double total_time;

    private FileWriter file_writer;

    public PIDFTuner(double kP, double kI, double kD, double kF, double delta_time, Context context, String file_name) throws IOException {

        this.kP = kP;
        this.kI = kI;
        this.kD = kD;
        this.kF = kF;

        this.delta_time = delta_time;
        total_time = 0.0;

        this.I = 0;

        File file = new File(context.getFilesDir(), file_name + ".csv");

        file_writer = new FileWriter(file);

        file_writer.append("time,targetVel,actualVel,P,I,D,F\n");

    }

    public void graph_PIDF(double current_velocity, double target_velocity) throws IOException {

        double error = target_velocity - current_velocity;

        double P = kP * error;

        I += kI * error * delta_time;

        double D = kD * (error - last_error) / delta_time;

        double F = kF * target_velocity;

        total_time += delta_time;

        last_error = error;

        file_writer.append(String.valueOf(total_time)).append(",").append(String.valueOf(target_velocity)).append(",").append(String.valueOf(current_velocity)).append(",").append(String.valueOf(P)).append(",").append(String.valueOf(I)).append(",").append(String.valueOf(D)).append(",").append(String.valueOf(F)).append("\n");

    }

    public void close() throws IOException {

        file_writer.flush();
        file_writer.close();

    }

}

