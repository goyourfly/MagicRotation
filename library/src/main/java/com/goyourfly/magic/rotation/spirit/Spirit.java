package com.goyourfly.magic.rotation.spirit;

import android.graphics.Canvas;

/**
 * Created by gaoyufei on 2017/4/7.
 * 这下面是喜欢乱动的小精灵球
 */

public abstract class Spirit {
    public static final int RADIUS = 20;
    // 精灵一共有以下几种状态
    public BaseState stateSmall = new SmallState(this);
    public BaseState stateMid = new MidState(this);
    public BaseState stateLarge = new LargeState(this);
    public BaseState stateSmallToMid = new SmallToMidState(this);
    public BaseState stateMidToSmall = new MidToSmallState(this);
    public BaseState stateMidToLarge = new MidToLargeState(this);
    public BaseState stateLargeToMid = new LargeToMidState(this);

    protected BaseState state = stateSmall;
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

    public void setState(BaseState state) {
        this.state = state;
    }

    Behave getBehave() {
        return behave;
    }

    public void transform() {
        state.transform();
    }


    public float getX(){
        return behave.x;
    }

    public float getY(){
        return behave.y;
    }

    /**
     * 返回当时实际的半径
     * @return
     */
    public float getRadius(){
        return behave.scale * RADIUS;
    }
}
