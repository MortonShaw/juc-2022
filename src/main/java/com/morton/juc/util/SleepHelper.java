package com.morton.juc.util;

import java.util.concurrent.TimeUnit;

/**
 * @author MortonShaw
 * @date 2021/7/25 23:14
 */
public class SleepHelper {

    public static void sleepSeconds(int i) {
        try {
            TimeUnit.SECONDS.sleep(i);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
