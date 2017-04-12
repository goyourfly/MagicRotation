package com.goyourfly.magic.rotation.spirit;

/**
 * Created by gaoyufei on 2017/4/7.
 */

public class SmallToMidState extends BaseState {
    public static final float SCALE_SPEED = 0.05f;

    public SmallToMidState(Spirit spirit) {
        super(spirit);
    }

    @Override
    public void transform() {
        setState(spirit.stateMid);
    }

    @Override
    public void measure() {
        super.measure();
        defaultTranslateMeasure();
        Behave behave = spirit.getBehave();

        if(behave.scale < MidState.SCALE){
            behave.scale += SCALE_SPEED;
        }else {
            transform();
        }

    }
}
