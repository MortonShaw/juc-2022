package com.morton.juc.c_001_00_thread_end;

import com.morton.juc.util.SleepHelper;

/**
 * @author MortonShaw
 * @date 2021/7/25 23:57
 */
public class T01_Stop {

    public static void main(String[] args) {
        Thread t = new Thread(() -> {
            while (true) {
                System.out.println("go on");
                SleepHelper.sleepSeconds(1);
            }
        });

        t.start();
        SleepHelper.sleepSeconds(5);
        t.stop();
    }

}
