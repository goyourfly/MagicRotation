package com.goyourfly.magic.rotation.line;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

import com.goyourfly.magic.rotation.Component;
import com.goyourfly.magic.rotation.spirit.Spirit;

/**
 * Created by gaoyufei on 2017/4/12.
 */

public class Line implements Component {
    private Paint mPaint;
    private Spirit spiritFrom;
    private Spirit spiritTo;
    private RectF innerBound;

    public Line(Spirit spiritFrom, Spirit spiritTo, RectF innerBound) {
        this.spiritFrom = spiritFrom;
        this.spiritTo = spiritTo;
        this.innerBound = innerBound;

        mPaint = new Paint();
        mPaint.setColor(Color.GRAY);
        mPaint.setAlpha(20);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(2);
    }


    @Override
    public void onDraw(Canvas canvas) {

        if(!isCross(spiritFrom.getX(),spiritFrom.getY(),spiritTo.getX(),spiritTo.getY(),innerBound.centerX(),innerBound.centerY(),innerBound.width()/2)){
            canvas.drawLine(spiritFrom.getX(), spiritFrom.getY(), spiritTo.getX(), spiritTo.getY(), mPaint);
        }
    }

    public boolean isCross(float x1, float y1, float x2, float y2, float centerX, float centerY,float radius) {
        double baX = x2 - x1;
        double baY = y2 - y1;
        double caX = centerX - x1;
        double caY = centerY - y1;

        double a = baX * baX + baY * baY;
        double bBy2 = baX * caX + baY * caY;
        double c = caX * caX + caY * caY - radius * radius;

        double pBy2 = bBy2 / a;
        double q = c / a;

        double disc = pBy2 * pBy2 - q;
        return disc >= 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Line line = (Line) o;

        if (spiritFrom != null ? !spiritFrom.equals(line.spiritFrom) : line.spiritFrom != null)
            return false;
        return spiritTo != null ? spiritTo.equals(line.spiritTo) : line.spiritTo == null;

    }

    @Override
    public int hashCode() {
        int result = spiritFrom != null ? spiritFrom.hashCode() : 0;
        result = 31 * result + (spiritTo != null ? spiritTo.hashCode() : 0);
        return result;
    }
}
