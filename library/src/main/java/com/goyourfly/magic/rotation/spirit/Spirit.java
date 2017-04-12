package com.goyourfly.magic.rotation.spirit;

import android.graphics.Canvas;
import android.view.MotionEvent;

import com.goyourfly.magic.rotation.Component;

/**
 * Created by gaoyufei on 2017/4/7.
 * 这下面是喜欢乱动的小精灵球
 */

public abstract class Spirit implements Component {
    public static final int RADIUS = 20;
    // 精灵一共有以下几种状态
    public BaseState stateSmall = new SmallState(this);
    public BaseState stateMid = new MidState(this);
    public BaseState stateLarge = new LargeState(this);
    public BaseState stateSmallToMid = new SmallToMidState(this);
    public BaseState stateMidToSmall = new MidToSmallState(this);
    public BaseState stateMidToLarge = new MidToLargeState(this);
    public BaseState stateLargeToMid = new LargeToMidState(this);
    public BaseState stateLargeToSmall = new LargeToSmallState(this);

    protected BaseState state = stateSmall;
    protected Behave behave = new Behave();
    protected float centerX;
    protected float centerY;
    protected float trackRadius;
    // 轨道左右最大限度随机性
    protected float trackRadiusSpace = 100;


    // 精灵的位置
    public void setCenter(float x, float y) {
        this.centerX = x;
        this.centerY = y;
    }

    public void setTrackRadius(float radius, float ringWidth) {
        this.trackRadius = radius;
        this.trackRadiusSpace = ringWidth / 2;
        ((LargeState) stateLarge).setTrackRadius(radius - trackRadiusSpace - RADIUS - 8);
    }


    // 精灵画自己
    public abstract void onDraw(Canvas canvas);

    public void setState(BaseState state) {
        if (this.state != null) {
            this.state.stateOut();
        }
        state.stateIn();
        this.state = state;
    }

    public BaseState getState(){
        return state;
    }

    Behave getBehave() {
        return behave;
    }

    public void transform() {
        state.transform();
    }


    public float getX() {
        return behave.x;
    }

    public float getY() {
        return behave.y;
    }

    /**
     * 返回当时实际的半径
     *
     * @return
     */
    public float getRadius() {
        return behave.scale * RADIUS;
    }

    public void onTouchEvent(MotionEvent event) {
        switch (isInRange(event)) {
            case CLICK_IN:
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    transform();
                } else if (event.getAction() == MotionEvent.ACTION_MOVE) {
                    if (state == stateSmall) {
                        transform();
                    }
                }
            case CLICK_NEAR:
//                setState(stateSmallToMid);
                break;
            case CLICK_NOTHING:
//                setState(stateMidToSmall);
                if (state == stateLarge) {
                    transform();
                }
                break;
        }
    }

    public int isInRange(MotionEvent event) {
        float figX = event.getX();
        float figY = event.getY();

        float x = getX();
        float y = getY();
        float radius = getRadius();

        if (Math.abs((figX - x)) < radius && Math.abs((figY - y)) < radius) {
            return CLICK_IN;
        }

        if (Math.abs((figX - x)) < radius * 3 && Math.abs((figY - y)) < radius * 3) {
            return CLICK_NEAR;
        }

        return CLICK_NOTHING;
    }

    // 点击到物体
    public static final int CLICK_IN = 1;
    //　靠近物体
    public static final int CLICK_NEAR = 2;
    // 无
    public static final int CLICK_NOTHING = 3;
}
