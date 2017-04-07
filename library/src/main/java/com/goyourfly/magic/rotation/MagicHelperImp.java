package com.goyourfly.magic.rotation;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
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

    @Override
    public void onTouch(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            Toast.makeText(mContext, "I am click", Toast.LENGTH_SHORT).show();

//            mSpirit.transform();
        }
    }
}
