package com.goyourfly.magic.rotation.spirit;

import android.graphics.Matrix;
import android.util.Log;
import android.widget.Toast;

import com.goyourfly.magic.rotation.utils.RandomUtils;

/**
 * Created by gaoyufei on 2017/4/7.
 */

public class LargeToMidState extends BaseState {

    private static  final  float SCALE_FRAME = 60;
    private static  final  float TRANSFORM_FRAME = 60;
    private int frame = 0;
    private float transSpeedX;
    private float transSpeedY;
    private float scaleSpeed;

    public LargeToMidState(Spirit spirit) {
        super(spirit);
    }

    @Override
    public void transform() {
//        setState(spirit.stateMid);
    }

    @Override
    public void stateIn() {
        super.stateIn();
        frame ++;
        Behave behave = spirit.getBehave();
        float transX = behave.matrixValue[Matrix.MTRANS_X];
        float transY = behave.matrixValue[Matrix.MTRANS_Y];
        float disX = behave.x - transX;
         transSpeedX = disX/ TRANSFORM_FRAME;
        float disY = behave.y - transY;
         transSpeedY = disY / TRANSFORM_FRAME;
        float maxScale = MidState.SCALE;
         scaleSpeed = (LargeState.SCALE - maxScale)  / SCALE_FRAME;

    }

    @Override
    public void stateOut() {
        super.stateOut();
        frame = 0;
    }

    @Override
    public void measure() {
        Behave behave = spirit.getBehave();

        if(frame < TRANSFORM_FRAME) {
            behave.x -= transSpeedX;
            behave.y -= transSpeedY;
        }else {
            float transX = behave.matrixValue[Matrix.MTRANS_X];
            float transY = behave.matrixValue[Matrix.MTRANS_Y];
            behave.x = transX;
            behave.y = transY;
        }


        if(frame < SCALE_FRAME) {
            behave.scale -= scaleSpeed;
        }else {
            behave.scale = MidState.SCALE;
        }

        if(frame > Math.max(SCALE_FRAME,TRANSFORM_FRAME)){
            setState(spirit.stateMid);
        }
        frame++;
    }
}
