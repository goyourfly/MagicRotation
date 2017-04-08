package com.goyourfly.magic.rotation;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by gaoyufei on 2017/4/7.
 */

public class MagicRotationView  extends View{
    private MagicHelper mHelper = new MagicHelperImp();
    public MagicRotationView(Context context) {
        super(context);
        mHelper.onViewCreate(context,null);
    }

    public MagicRotationView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mHelper.onViewCreate(context,attrs);
    }

    public MagicRotationView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mHelper.onViewCreate(context,attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mHelper.onMeasure(MeasureSpec.getSize(widthMeasureSpec),
                MeasureSpec.getSize(heightMeasureSpec),
                getPaddingLeft(),
                getPaddingTop(),
                getPaddingRight(),
                getPaddingBottom());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mHelper.onDraw(canvas);
        invalidate();
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mHelper.onTouch(event);
        return true;
    }
}
