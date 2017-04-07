package com.goyourfly.magic.rotation.spirit;

import android.graphics.Canvas;

/**
 * Created by gaoyufei on 2017/4/7.
 * 这下面是喜欢乱动的小精灵球
 */

public abstract class Spirit {
    // 精灵一共有以下几种状态
    public State stateSmall = new SmallState(this);
    public State stateMid = new MidState(this);
    public State stateLarge = new LargeState(this);
    public State stateSmallToMid = new SmallToMidState(this);
    public State stateMidToSmall = new MidToSmallState(this);
    public State stateMidToLarge = new MidToLargeState(this);
    public State stateLargeToMid = new LargeToMidState(this);

    protected State state = stateSmall;
    protected Behave behave = new Behave();
    protected float centerX;
    protected float centerY;
    protected float trackRadius;
    // 轨道左右最大限度随机性
    protected float trackRadiusSpace = 100;

    // 精灵的位置
    public abstract void setCenter(float x, float y);

    public abstract void setTrackRadius(float radius);

    // 精灵画自己
    public abstract void onDraw(Canvas canvas);

    protected void setState(State state) {
        this.state = state;
    }

    Behave getBehave() {
        return behave;
    }

    public void transform() {
        state.transform();
    }

}
