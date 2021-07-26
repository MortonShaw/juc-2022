package com.morton.juc.c_001_03_Ordering;

import java.io.IOException;

/**
 * @author MortonShaw
 * @date 2021/7/26 10:04
 */
public class T03_ThisEscape {

    private int num = 8;

    public T03_ThisEscape() {
        new Thread(() -> {
            System.out.println(this.num);
        }).start();
    }

    public static void main(String[] args) throws IOException {
        new T03_ThisEscape();
        System.in.read();
    }

}
