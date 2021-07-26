package com.morton.juc.c_001_00_Volatile;

import com.morton.juc.util.SleepHelper;

/**
 * volatile引用类型（包括数组），只能保证引用本身的可见性，不能保证内部字段的可见性
 *
 * @author MortonShaw
 * @date 2021/7/26 9:46
 */
public class T02_VolatileReference {

    private static class A {
        volatile boolean running = true;

        void m() {
            System.out.println("m start!");
            while (running) {

            }
            System.out.println("m end!");
        }
    }

    private static volatile A a = new A();

    public static void main(String[] args) {
        new Thread(a::m, "t1").start();
        SleepHelper.sleepSeconds(1);
        a.running = false;
    }

}
