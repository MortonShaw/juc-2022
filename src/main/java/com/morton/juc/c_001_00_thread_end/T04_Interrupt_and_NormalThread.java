package com.morton.juc.c_001_00_thread_end;

import com.morton.juc.util.SleepHelper;

/**
 * @author MortonShaw
 * @date 2021/7/25 23:57
 */
public class T04_Interrupt_and_NormalThread {

    public static void main(String[] args) {
        Thread t = new Thread(() -> {
            while (!Thread.interrupted()) {

            }
            System.out.println("t end!");
        });

        t.start();
        SleepHelper.sleepSeconds(1);
        t.interrupt();
    }

}
