package com.morton.juc.c_001_00_Volatile;

import com.morton.juc.util.SleepHelper;

/**
 * @author MortonShaw
 * @date 2021/7/26 9:36
 */
public class T01_HelloVolatile {

    private static /*volatile*/ boolean running = true;

    private static void m() {
        System.out.println("m start");
        while (running) {
            System.out.println("hello");
        }
        System.out.println("m end!");
    }

    public static void main(String[] args) {
        new Thread(T01_HelloVolatile::m, "t1").start();

        SleepHelper.sleepSeconds(1);

        running = false;
    }

}
