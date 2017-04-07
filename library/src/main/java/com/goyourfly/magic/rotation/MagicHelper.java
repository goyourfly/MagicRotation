package com.goyourfly.magic.rotation;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by gaoyufei on 2017/4/7.
 */

public interface MagicHelper {

    public void onViewCreate(Context context, AttributeSet attrs);

    public void onMeasure(int width,int height,int paddingLeft,int paddingTop,int paddingRight,int paddingBottom);

    public void onDraw(Canvas canvas);

    public void onTouch(MotionEvent event);
}
