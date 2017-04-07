package com.goyourfly.magic.rotation.spirit;

import android.util.Log;

import com.goyourfly.magic.rotation.spirit.Spirit;
import com.goyourfly.magic.rotation.spirit.State;

/**
 * Created by gaoyufei on 2017/4/7.
 */

public class SmallToMidState extends State {

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
        Behave behave = spirit.getBehave();
    }
}
