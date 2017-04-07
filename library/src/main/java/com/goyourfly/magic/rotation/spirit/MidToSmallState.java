package com.goyourfly.magic.rotation.spirit;

/**
 * Created by gaoyufei on 2017/4/7.
 */

public class MidToSmallState extends State {
    public MidToSmallState(Spirit spirit) {
        super(spirit);
    }

    @Override
    public void transform() {
        setState(spirit.stateSmall);
    }

    @Override
    public void measure() {
        super.measure();
        Behave behave = spirit.getBehave();
    }
}
