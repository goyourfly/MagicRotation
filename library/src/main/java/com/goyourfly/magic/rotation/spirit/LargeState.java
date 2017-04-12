package com.goyourfly.magic.rotation.spirit;

import com.goyourfly.magic.rotation.utils.RandomUtils;

/**
 * Created by gaoyufei on 2017/4/7.
 */

public class LargeState  extends BaseState {
    public static  float SCALE;
    public LargeState(Spirit spirit) {
        super(spirit);
    }

    public void setTrackRadius(float trackRadius){
        SCALE = trackRadius/spirit.RADIUS;
    }

    @Override
    public void transform() {
        if(RandomUtils.nextInt(0,10) < 0) {
            setState(spirit.stateLargeToMid);
        }else {
            setState(spirit.stateLargeToSmall);
        }
    }

    @Override
    public void measure() {
//        super.measure();
    }
}
