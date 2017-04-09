package com.goyourfly.magic.rotation.spirit;

/**
 * Created by gaoyufei on 2017/4/7.
 */

public class SmallState extends BaseState {
    public static final float SCALE = 1;
    public SmallState(Spirit spirit) {
        super(spirit);
    }

    @Override
    public void transform() {
        setState(spirit.stateSmallToMid);
    }

    @Override
    public void measure() {
        super.measure();
        defaultTranslateMeasure();
    }
}
