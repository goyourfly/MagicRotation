package com.goyourfly.magic.rotation.spirit;

/**
 * Created by gaoyufei on 2017/4/7.
 */

public class MidState extends BaseState {
    public static final float SCALE = 3;
    public MidState(Spirit spirit) {
        super(spirit);
    }

    @Override
    public void transform() {
        setState(spirit.stateMidToLarge);
    }

    @Override
    public void measure() {
        super.measure();
        defaultTranslateMeasure();
        Behave behave = spirit.getBehave();
    }
}
