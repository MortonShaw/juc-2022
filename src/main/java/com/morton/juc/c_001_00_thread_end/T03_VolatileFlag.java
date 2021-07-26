package com.morton.juc.c_001_00_thread_end;

import com.morton.juc.util.SleepHelper;

/**
 * @author MortonShaw
 * @date 2021/7/25 23:57
 */
public class T03_VolatileFlag {

    private static volatile boolean running = true;

    public static void main(String[] args) {
        Thread t = new Thread(() -> {
            long i = 0L;
            while (running) {
                i++;
            }
            System.out.println("end and i = " + i);
        });

        t.start();
        SleepHelper.sleepSeconds(1);
        running = false;
    }

}
