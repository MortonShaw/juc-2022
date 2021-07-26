package com.morton.juc.c_000_threadbasic;

import com.morton.juc.util.SleepHelper;

/**
 * @author MortonShaw
 * @date 2021/7/25 23:34
 */
public class T07_Interrupt_and_sleep {

    public static void main(String[] args) {
        Thread t = new Thread(() -> {
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                System.out.println("Thread is interrupted!");
                System.out.println(Thread.currentThread().isInterrupted());
            }
        });

        t.start();
        SleepHelper.sleepSeconds(2);
        t.interrupt();
    }

}
