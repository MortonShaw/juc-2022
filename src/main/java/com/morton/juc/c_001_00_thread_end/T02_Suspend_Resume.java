package com.morton.juc.c_001_00_thread_end;

import com.morton.juc.util.SleepHelper;

import java.util.Objects;

/**
 * @author MortonShaw
 * @date 2021/7/25 23:57
 */
public class T02_Suspend_Resume {

    public static void main(String[] args) {
        Thread t = new Thread(() -> {
            while (true) {
                System.out.println("go on");
                SleepHelper.sleepSeconds(1);
            }
        });

        t.start();
        SleepHelper.sleepSeconds(5);
        t.suspend();
        SleepHelper.sleepSeconds(5);
        t.resume();
    }

}
