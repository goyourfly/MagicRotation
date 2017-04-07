package com.goyourfly.magic.rotation.spirit;

/**
 * Created by gaoyufei on 2017/4/7.
 */

public class MidState extends State {
    public MidState(Spirit spirit) {
        super(spirit);
    }

    @Override
    public void transform() {
        setState(spirit.stateMidToSmall);
    }

    @Override
    public void measure() {
        super.measure();
        Behave behave = spirit.getBehave();
    }
}
