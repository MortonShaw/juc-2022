package com.morton.juc.c_000_threadbasic;

import com.morton.juc.util.SleepHelper;

/**
 * @author MortonShaw
 * @date 2021/7/25 23:34
 */
public class T06_Interrupt_and_interrupted {

    public static void main(String[] args) {
        Thread t = new Thread(() -> {
            while (true) {
                if (Thread.interrupted()) {
                    System.out.println("Thread is interrupted!");
                    System.out.println(Thread.interrupted());
                    break;
                }
            }
        });

        t.start();
        SleepHelper.sleepSeconds(2);
        t.interrupt();
        System.out.println("main : " + Thread.interrupted());
    }

}
