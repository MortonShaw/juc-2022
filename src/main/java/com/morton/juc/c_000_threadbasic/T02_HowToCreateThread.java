package com.morton.juc.c_000_threadbasic;

import com.sun.webkit.ThemeClient;

import java.util.concurrent.*;

/**
 * @author MortonShaw
 * @date 2021/7/25 22:56
 */
public class T02_HowToCreateThread {

    static class Mythread extends Thread {
        @Override
        public void run() {
            System.out.println("Hello MyThread!");
        }
    }

    static class MyRun implements Runnable {
        @Override
        public void run() {
            System.out.println("Hello MyRun!");
        }
    }

    static class MyCall implements Callable<String> {
        @Override
        public String call() throws Exception {
            System.out.println("Hello MyCall");
            return "success";
        }
    }

    // 启动线程的5种方式
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        new Mythread().start();
        new Thread(new MyRun()).start();
        new Thread(() -> {
            System.out.println("Hello lambda!");
        }).start();

        FutureTask<String> futureTask = new FutureTask<>(new MyCall());
        Thread t = new Thread(futureTask);
        t.start();
        System.out.println(futureTask.get());

        ExecutorService service = Executors.newCachedThreadPool();
        service.execute(() -> {
            System.out.println("Hello ThreadPool");
        });
        Future<String> submit = service.submit(new MyCall());
        System.out.println(submit.get());
        service.shutdown();
    }


}
