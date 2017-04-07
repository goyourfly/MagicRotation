package com.goyourfly.magic.rotation.spirit;

import com.goyourfly.magic.rotation.spirit.Spirit;
import com.goyourfly.magic.rotation.spirit.State;

/**
 * Created by gaoyufei on 2017/4/7.
 */

public class SmallState extends State {
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
        defaultMeasure();
        Behave behave = spirit.getBehave();
    }
}
