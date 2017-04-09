package com.goyourfly.magic.rotation.spirit;

/**
 * Created by gaoyufei on 2017/4/7.
 */

public class MidToLargeState extends BaseState {
    private static final float TRANSFORM_FRAME = 10;
    private static final float SCALE_FRAME = 40;
    public MidToLargeState(Spirit spirit) {
        super(spirit);
    }

    @Override
    public void transform() {
        setState(spirit.stateLarge);
    }

    @Override
    public void measure() {
//        super.measure();
        Behave behave = spirit.getBehave();

        float disX = behave.x - spirit.centerX;
        float transSpeedX = disX/ TRANSFORM_FRAME;
        if(Math.abs(disX) > Math.abs(transSpeedX)){
            behave.x -= transSpeedX;
        }else {
            behave.x = spirit.centerX;
        }

        float disY = behave.y - spirit.centerY;
        float transSpeedY = disY / TRANSFORM_FRAME;
        if(Math.abs(disY) > Math.abs(transSpeedY)){
            behave.y -= transSpeedY;
        }else {
            behave.y = spirit.centerY;
        }

        float scaleSpeed = LargeState.SCALE / SCALE_FRAME;
        if(behave.scale < LargeState.SCALE){
            behave.scale += scaleSpeed;
        }else {
            behave.scale = LargeState.SCALE;
        }

        if(behave.scale == LargeState.SCALE){
            transform();
        }

    }
}
