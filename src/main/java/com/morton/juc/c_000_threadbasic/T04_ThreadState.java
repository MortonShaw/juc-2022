package com.morton.juc.c_000_threadbasic;

import com.morton.juc.util.SleepHelper;

import java.sql.Time;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author MortonShaw
 * @date 2021/7/25 23:11
 */
public class T04_ThreadState {

    public static void main(String[] args) throws InterruptedException {
        // ===========================================
        Thread t1 = new Thread(() -> {
            System.out.println("2:" + Thread.currentThread().getState());
            for (int i = 0; i < 3; i++) {
                SleepHelper.sleepSeconds(i);
                System.out.println(i + " ");
            }
            System.out.println();
        });
        System.out.println("1:" + t1.getState());
        t1.start();
        t1.join();
        System.out.println("3:" + t1.getState());

        // ===========================================
        Thread t2 = new Thread(() -> {
            try {
                LockSupport.park();
                System.out.println("t2 go on!");
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        t2.start();

        TimeUnit.SECONDS.sleep(1);
        System.out.println("4:" + t2.getState());

        LockSupport.unpark(t2);
        TimeUnit.SECONDS.sleep(1);
        System.out.println("5:" + t2.getState());
        // ===========================================
        final Object o = new Object();
        Thread t3 = new Thread(() -> {
           synchronized (o) {
               System.out.println("t3得到了锁");
           }
        });

        new Thread(() -> {
           synchronized (o) {
               SleepHelper.sleepSeconds(5);
           }
        }).start();

        SleepHelper.sleepSeconds(1);
        t3.start();
        SleepHelper.sleepSeconds(1);
        System.out.println("6:" + t3.getState());
        // ===========================================
        final Lock lock = new ReentrantLock();
        Thread t4 = new Thread(() -> {
            lock.lock(); // 省略 try finally
            System.out.println("t4得到了锁 o");
            lock.unlock();
        });

        new Thread(() -> {
            lock.lock();
            SleepHelper.sleepSeconds(5);
            lock.unlock();
        }).start();

        SleepHelper.sleepSeconds(1);
        t4.start();
        SleepHelper.sleepSeconds(1);
        System.out.println("7:" + t4.getState());
        // ===========================================
        Thread t5 = new Thread(() -> {
            LockSupport.park();
        });

        t5.start();
        SleepHelper.sleepSeconds(1);
        System.out.println("8:" + t5.getState());
        LockSupport.unpark(t5);
    }


}
