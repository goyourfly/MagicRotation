package com.goyourfly.magic.rotation.spirit;

import android.graphics.Matrix;

import com.goyourfly.magic.rotation.utils.RandomUtils;

import java.util.Random;

/**
 * Created by gaoyufei on 2017/4/7.
 */

public class Behave {
    float angle = RandomUtils.nextFloat(0,360);
    float angleSpeed = 0;
    float latestTrackRadius = 0;

    Matrix matrix = new Matrix();
    float[] matrixValue = new float[9];
}
