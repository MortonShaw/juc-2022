package com.morton.juc.c_000_threadbasic;

import java.text.DecimalFormat;
import java.util.Random;
import java.util.concurrent.CountDownLatch;

/**
 * @author MortonShaw
 * @date 2021/7/25 22:36
 */
public class T01_MultiVSSingle_ContextSwitch {
    // ==============================================================
    private static double[] nums = new double[1_0000_0000];
    private static Random r = new Random();
    private static DecimalFormat df = new DecimalFormat("0.00");

    static {
        for (int i = 0; i < nums.length; i++) {
            nums[i] = r.nextDouble();
        }
    }

    private static void m1() {
        long start = System.currentTimeMillis();
        double result = 0.0;

        for (int i = 0; i < nums.length; i++) {
            result += nums[i];
        }

        long end = System.currentTimeMillis();

        System.out.println("m1 = " + (end - start) + " result = " + df.format(result));
    }
    // ==============================================================

    static double result1 = 0.0, result2 = 0.0, result = 0.0;

    private static void m2() throws InterruptedException {
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < nums.length / 2; i++) {
                result1 += nums[i];
            }
        });
        Thread t2 = new Thread(() -> {
            for (int i = nums.length / 2; i < nums.length; i++) {
                result2 += nums[i];
            }
        });

        long start = System.currentTimeMillis();
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        long end = System.currentTimeMillis();

        result = result1 + result2;
        System.out.println("m2 = " + (end - start) + " result = " + df.format(result));
    }

    private static void m3() throws InterruptedException {
        final int threadCount = 8;
        Thread[] threads = new Thread[threadCount];
        double[] results = new double[threadCount];
        final int segmentCount = nums.length / threadCount;
        CountDownLatch latch = new CountDownLatch(threadCount);

        for (int i = 0; i < threadCount; i++) {
            int m = i;
            threads[i] = new Thread(() -> {
                for (int j = m * segmentCount; j < (m + 1) * segmentCount && j < nums.length; j++) {
                    results[m] += nums[j];
                }
            });

            latch.countDown();
        }

        double resultM3 = 0.0;
        long start = System.currentTimeMillis();
        for (Thread t : threads) {
            t.start();
        }
        latch.await();
        for (int i = 0; i < results.length; i++) {
            resultM3 += results[i];
        }
        long end = System.currentTimeMillis();


        System.out.println("m3 = " + (end - start) + " result = " + df.format(result));
    }

    public static void main(String[] args) throws InterruptedException {
        m1();
        m2();
        m3();
    }
}
