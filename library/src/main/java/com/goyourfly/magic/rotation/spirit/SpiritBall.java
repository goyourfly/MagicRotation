package com.goyourfly.magic.rotation.spirit;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.os.SystemClock;
import android.util.Log;

import com.goyourfly.magic.rotation.utils.RandomUtils;

import java.util.Random;

/**
 * Created by gaoyufei on 2017/4/7.
 */

public class SpiritBall extends Spirit {
    public static final String TAG = "SpiritBall";

    private static final long STATE_CHANGE_INTERVAL = 2000;
    private long lastStateChangeTime = System.currentTimeMillis();

    private Paint paint;
    private Paint paintInner;
    private float strokeWidth = 8;

    public SpiritBall() {
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStrokeWidth(strokeWidth);
        paint.setAlpha(100);
        int[] color = {Color.BLACK, Color.BLUE, Color.RED, Color.GRAY, Color.GREEN, Color.YELLOW, Color.CYAN, Color.MAGENTA};
        paint.setColor(color[new Random().nextInt(color.length)]);
        paint.setStyle(Paint.Style.STROKE);

        paintInner = new Paint();
        paintInner.setStyle(Paint.Style.FILL);
        paintInner.setColor(Color.WHITE);
        paintInner.setAntiAlias(true);
    }


    @Override
    public void onDraw(Canvas canvas) {
        state.measure();
        Behave behave = getBehave();
        canvas.drawCircle(behave.x, behave.y, behave.scale * RADIUS, paintInner);
        canvas.drawCircle(behave.x, behave.y, behave.scale * RADIUS, paint);


        if (System.currentTimeMillis() - lastStateChangeTime > STATE_CHANGE_INTERVAL) {
            lastStateChangeTime = System.currentTimeMillis();
            if (RandomUtils.nextInt(0, 50) < 1) {
                if (state == stateSmall) {
                    state.transform();
                } else if (state == stateMid) {
                    state.setState(stateMidToSmall);
                }
            }
        }
    }
}
