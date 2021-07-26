package com.morton.juc.c_000_threadbasic;

import com.morton.juc.util.SleepHelper;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author MortonShaw
 * @date 2021/7/25 23:34
 */
public class T11_Interrupt_and_lockInterruptibly {

    private static ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            lock.lock();
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
            System.out.println("t1 end!");
        });

        t1.start();
        SleepHelper.sleepSeconds(1);

        Thread t2 = new Thread(() -> {
            System.out.println("t2 start!");
            try {
                lock.lockInterruptibly();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
            System.out.println("t2 end!");
        });
        t2.start();
        t2.interrupt();
    }

}
