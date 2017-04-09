package com.goyourfly.magic.rotation.spirit;

import android.graphics.Matrix;
import android.util.Log;

/**
 * Created by gaoyufei on 2017/4/7.
 */

public class LargeToSmallState extends BaseState {

    private static  final  float SCALE_FRAME = 10;
    private static  final  float TRANSFORM_FRAME = 40;

    public LargeToSmallState(Spirit spirit) {
        super(spirit);
    }

    @Override
    public void transform() {
//        setState(spirit.stateMid);
    }


    @Override
    public void measure() {
        Behave behave = spirit.getBehave();
        Matrix matrix = behave.matrix;
        matrix.setTranslate(spirit.centerX, spirit.centerY);
        matrix.preRotate(behave.angle);
        matrix.preTranslate(behave.latestTrackRadius, 0);
        matrix.getValues(behave.matrixValue);

        float transX = behave.matrixValue[Matrix.MTRANS_X];
        float transY = behave.matrixValue[Matrix.MTRANS_Y];

        float disX = behave.x - transX;
        float transSpeedX = disX/ TRANSFORM_FRAME;
        if(Math.abs(disX) > Math.abs(transSpeedX)){
            behave.x -= transSpeedX;
        }else {
            behave.x = spirit.centerX;
        }

        float disY = behave.y - transY;
        float transSpeedY = disY / TRANSFORM_FRAME;
        if(Math.abs(disY) > Math.abs(transSpeedY)){
            behave.y -= transSpeedY;
        }else {
            behave.y = spirit.centerY;
        }

        float scaleSpeed = (behave.scale - SmallState.SCALE) / SCALE_FRAME;
        if(behave.scale > SmallState.SCALE){
            behave.scale -= scaleSpeed;
        }else {
            behave.scale = SmallState.SCALE;
        }


        if(Math.abs(behave.scale - SmallState.SCALE) <= 0.1f
                && Math.abs(disX) < 1f
                && Math.abs(disY) < 1f){
            setState(spirit.stateSmall);
        }
    }
}
