package com.morton.juc.c_000_threadbasic;

import com.morton.juc.util.SleepHelper;
import javafx.beans.property.adapter.ReadOnlyJavaBeanBooleanProperty;

/**
 * @author MortonShaw
 * @date 2021/7/25 23:34
 */
public class T08_Interrupt_and_wait {

    private static Object o = new Object();

    public static void main(String[] args) {
        Thread t = new Thread(() -> {
            synchronized (o) {
                try {
                    o.wait();
                } catch (InterruptedException e) {
                    System.out.println("Thread is interrupted!");
                    System.out.println(Thread.currentThread().isInterrupted());
                }
            }
        });

        t.start();
        SleepHelper.sleepSeconds(2);
        t.interrupt();
    }

}
