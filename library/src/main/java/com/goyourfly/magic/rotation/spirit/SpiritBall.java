package com.goyourfly.magic.rotation.spirit;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.Log;

import java.util.Random;

/**
 * Created by gaoyufei on 2017/4/7.
 */

public class SpiritBall extends Spirit {
    public static final String TAG = "SpiritBall";


    private Paint paint;

    public SpiritBall() {
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStrokeWidth(8);
        paint.setAlpha(100);
        int[] color = {Color.BLACK,Color.BLUE,Color.RED,Color.GRAY,Color.GREEN,Color.YELLOW,Color.CYAN,Color.MAGENTA};
        paint.setColor(color[new Random().nextInt(color.length)]);
        paint.setStyle(Paint.Style.STROKE);
    }

    @Override
    public void setCenter(float x, float y) {
        this.centerX = x;
        this.centerY = y;
    }

    @Override
    public void setTrackRadius(float radius) {
        this.trackRadius = radius;
    }

    @Override
    public void onDraw(Canvas canvas) {
        state.measure();
        Behave behave = getBehave();
        canvas.drawCircle(behave.x,behave.y,behave.scale * RADIUS, paint);
    }
}
