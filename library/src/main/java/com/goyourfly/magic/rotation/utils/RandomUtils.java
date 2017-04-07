package com.goyourfly.magic.rotation.utils;

import java.util.Random;

/**
 * Created by gaoyufei on 2017/4/7.
 */

public class RandomUtils {
    private static final Random random = new Random();

    public static float nextFloat(){
        return nextFloat(0,1);
    }

    public static float nextFloat(float from,float to){
        return from + random.nextFloat() * (to - from);
    }

    public static int nextInt(int from, int to){
        return (int) nextFloat(from,to);
    }



}
