package com.goyourfly.magic.rotation;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.Toast;

import com.goyourfly.magic.rotation.spirit.Spirit;
import com.goyourfly.magic.rotation.spirit.SpiritBall;

/**
 * Created by gaoyufei on 2017/4/7.
 */

public class MagicHelperImp implements MagicHelper {
    // 环形轨道的宽度
    private static final int mMaxRandomSize = 200;
    Context mContext;
    // View的宽度和高度
    int mWidth;
    int mHeight;

    // 环形轨迹的最外层和最内侧范围
    RectF mOuterBound;
    RectF mInnerBound;

    Paint mPaintHelpLine;

    Spirit[] mSpirits = new Spirit[20];

    Handler mHandler = new Handler();

    @Override
    public void onViewCreate(Context context, AttributeSet attrs) {
        mContext = context;
        mPaintHelpLine = new Paint();
        mPaintHelpLine.setAntiAlias(true);
        mPaintHelpLine.setColor(Color.LTGRAY);
        mPaintHelpLine.setStrokeWidth(1);
        mPaintHelpLine.setStyle(Paint.Style.STROKE);

        for (int i = 0; i < mSpirits.length; i++){
            mSpirits[i] = new SpiritBall();
        }
    }

    @Override
    public void onMeasure(int width,
                          int height,
                          int paddingLeft,
                          int paddingTop,
                          int paddingRight,
                          int paddingBottom) {
        this.mWidth = width;
        this.mHeight = height;

        if (mOuterBound == null || mInnerBound == null) {
            int size = Math.min(width, height);
            mOuterBound = new RectF(paddingLeft, paddingTop, size - paddingRight, size - paddingBottom);
            mInnerBound = new RectF(mOuterBound);
            mInnerBound.inset(mMaxRandomSize, mMaxRandomSize);

            for (Spirit spirit : mSpirits){
                spirit.setCenter(mInnerBound.centerX(), mInnerBound.centerY());
                spirit.setTrackRadius((mInnerBound.width() + mOuterBound.width()) / 4);
            }

        }
    }

    @Override
    public void onDraw(Canvas canvas) {
        //绘制两个限制小球移动范围的圆环
        canvas.drawCircle(mInnerBound.centerX(), mInnerBound.centerY(), mInnerBound.width() / 2, mPaintHelpLine);
        canvas.drawCircle(mOuterBound.centerX(), mOuterBound.centerY(), mOuterBound.width() / 2, mPaintHelpLine);

        for (Spirit spirit : mSpirits){
            spirit.onDraw(canvas);
        }
    }

    private Runnable mSmallCallback = new Runnable() {
        @Override
        public void run() {
            for (Spirit spirit : mSpirits){
                spirit.setState(spirit.stateMidToSmall);
            }
        }
    };

    @Override
    public void onTouch(MotionEvent event) {

            for (Spirit spirit : mSpirits){
                switch (isInRange(event,spirit)){
                    case CLICK_IN:
                    case CLICK_NEAR:
                        spirit.setState(spirit.stateSmallToMid);
                        break;
                    case CLICK_NOTHING:
                        spirit.setState(spirit.stateMidToSmall);
                        break;
                }
            }


        if(event.getAction() == MotionEvent.ACTION_DOWN){
            mHandler.removeCallbacks(mSmallCallback);
        }
        if(event.getAction() == MotionEvent.ACTION_UP
                || event.getAction() == MotionEvent.ACTION_CANCEL){
            mHandler.postDelayed(mSmallCallback,2000);
        }
    }

    public int isInRange(MotionEvent event,Spirit spirit){
        float figX = event.getX();
        float figY = event.getY();

        float x = spirit.getX();
        float y = spirit.getY();
        float radius = spirit.getRadius();

        if(Math.abs((figX - x)) < radius && Math.abs((figY - y)) < radius){
            return CLICK_IN;
        }

        if(Math.abs((figX - x)) < radius * 3 && Math.abs((figY - y)) < radius * 3){
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
