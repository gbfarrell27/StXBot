package org.firstinspires.ftc.teamcode.libs.components;

import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.LLResultTypes;
import com.qualcomm.hardware.limelightvision.Limelight3A;

import org.firstinspires.ftc.teamcode.libs.templates.XOpMode;

import java.util.ArrayList;
import java.util.List;

public class XCamera {

    private final XOpMode op;

    private Limelight3A limelight;

    private final String camera_name;

    private int index;

    private List<Integer> tag_IDs = new ArrayList<>();
    private List<Double> tag_X_degrees = new ArrayList<>();
    private List<Double> tag_Y_degrees = new ArrayList<>();
    private List<Double> tag_X_positions = new ArrayList<>();
    private List<Double> tag_Y_positions = new ArrayList<>();

    private List<Double> blob_X_degrees = new ArrayList<>();
    private List<Double> blob_Y_degrees = new ArrayList<>();
    private List<Double> blob_X_positions = new ArrayList<>();
    private List<Double> blob_Y_positions = new ArrayList<>();

    public XCamera(XOpMode op, String camera_name) {

        this.op = op;

        this.camera_name = camera_name;

    }

    public void init() {

        limelight = op.hardwareMap.get(Limelight3A.class, camera_name);
        limelight.start();
        index = 0;
        limelight.pipelineSwitch(index);

    }

    public void set_pipeline(int index) {

        this.index = index;

        limelight.pipelineSwitch(index);

    }

    public void get_tag_data(){

        LLResult result = limelight.getLatestResult();

        if(result != null && result.isValid()) {

            tag_IDs.clear();
            tag_X_degrees.clear();
            tag_Y_degrees.clear();
            tag_X_positions.clear();
            tag_Y_positions.clear();

            List<LLResultTypes.FiducialResult> fiducial_results = result.getFiducialResults();

            for(LLResultTypes.FiducialResult fiducial_result : fiducial_results){

                int fiducial_id = fiducial_result.getFiducialId();

                double txdeg = fiducial_result.getTargetXDegrees();
                double tydeg = fiducial_result.getTargetYDegrees();

                double tx = fiducial_result.getTargetPoseCameraSpace().getPosition().x;
                double ty = fiducial_result.getTargetPoseCameraSpace().getPosition().y;

                tag_IDs.add(fiducial_id);
                tag_X_degrees.add(txdeg);
                tag_Y_degrees.add(tydeg);
                tag_X_positions.add(tx);
                tag_Y_positions.add(ty);

            }


        }

    }

    public void get_color_data(){

        LLResult result = limelight.getLatestResult();

        if(result != null && result.isValid()) {

            blob_X_degrees.clear();
            blob_Y_degrees.clear();
            blob_X_positions.clear();
            blob_Y_positions.clear();

            List<LLResultTypes.ColorResult> color_results = result.getColorResults();

            for(LLResultTypes.ColorResult color_result : color_results){

                double txdeg = color_result.getTargetXDegrees();
                double tydeg = color_result.getTargetYDegrees();

                double tx = color_result.getTargetPoseCameraSpace().getPosition().x;
                double ty = color_result.getTargetPoseCameraSpace().getPosition().y;

                blob_X_degrees.add(txdeg);
                blob_Y_degrees.add(tydeg);
                blob_X_positions.add(tx);
                blob_Y_positions.add(ty);

            }

        }

    }






}
