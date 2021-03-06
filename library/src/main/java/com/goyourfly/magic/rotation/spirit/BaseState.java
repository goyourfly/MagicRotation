package com.goyourfly.magic.rotation.spirit;

import android.animation.ValueAnimator;
import android.graphics.Matrix;

import com.goyourfly.magic.rotation.utils.RandomUtils;

/**
 * Created by gaoyufei on 2017/4/7.
 * 定义一个状态机，在不同的操作下触发不同的状态
 */

public abstract class BaseState {
    Spirit spirit;

    ValueAnimator animatorAngle;
    ValueAnimator animatorTranslate;

    public BaseState(Spirit spirit) {
        this.spirit = spirit;
    }

    public void setState(BaseState state) {
        spirit.setState(state);
    }

    // 切换状态
    public abstract void transform();

    // 开始进入这个状态
    public void stateIn(){

    }

    // 离开这个状态
    public void stateOut(){

    }

    // 每个实现Measure要强制调用super.measure();
    public void measure() {
        final Behave behave = spirit.getBehave();
        if (animatorAngle == null
                || !animatorAngle.isRunning()) {
            animatorAngle = ValueAnimator.ofFloat(behave.angleSpeed, RandomUtils.nextFloat(0, 0.4f));
            animatorAngle.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    behave.angleSpeed = (float) animation.getAnimatedValue();
                }
            });
            animatorAngle.setDuration(RandomUtils.nextInt(1000, 1500));
            animatorAngle.start();
        }


        if (animatorTranslate == null
                || !animatorTranslate.isRunning()) {
            float targetRandom = 0;
            int nextInt = RandomUtils.nextInt(0, 20);
            if (nextInt < 3) {
                targetRandom = RandomUtils.nextFloat(-0.5f, 0.5f);
            } else if(nextInt < 6){
                targetRandom = RandomUtils.nextFloat(-0.2f, 0.1f);
            } else if(nextInt < 10){
                targetRandom = RandomUtils.nextFloat(0f, 1f);
            }else if(nextInt < 15){
                targetRandom = RandomUtils.nextFloat(-0.5f,0f);
            }else if(nextInt < 18){
                targetRandom = RandomUtils.nextFloat(0,0.3f);
            }else {
                targetRandom = RandomUtils.nextFloat(-1f,1f);
            }

            float targetTransform = behave.latestTrackRadius + targetRandom * spirit.trackRadiusSpace;
            if(targetTransform > spirit.trackRadius + spirit.trackRadiusSpace){
                targetTransform = spirit.trackRadius + spirit.trackRadiusSpace;
            }else if(targetTransform < spirit.trackRadius - spirit.trackRadiusSpace){
                targetTransform = spirit.trackRadius - spirit.trackRadiusSpace;
            }

            animatorTranslate = ValueAnimator.ofFloat(behave.latestTrackRadius, targetTransform);
            animatorTranslate.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    behave.latestTrackRadius = (float) animation.getAnimatedValue();
                }
            });

            animatorTranslate.setDuration(RandomUtils.nextInt(1000, 1500));
            animatorTranslate.start();
        }

        behave.angle += behave.angleSpeed;
        if (behave.angle > 360) {
            behave.angle = 0f;
        }
    }

    public void defaultTranslateMeasure() {
        Behave behave = spirit.getBehave();
        Matrix matrix = behave.matrix;
        matrix.setTranslate(spirit.centerX, spirit.centerY);
        matrix.preRotate(behave.angle);
        matrix.preTranslate(behave.latestTrackRadius, 0);
        matrix.getValues(behave.matrixValue);


        float transX = behave.matrixValue[Matrix.MTRANS_X];
        float transY = behave.matrixValue[Matrix.MTRANS_Y];

        behave.x = transX;
        behave.y = transY;

    }

    public void defaultScaleMeasure(){
        Behave behave = spirit.getBehave();
        behave.scale = 1;
    }

}
