package com.syb.concurrent.cas_demo;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

public class CAS1 {


    private static volatile int m = 0;

    private static AtomicInteger atomicInteger = new AtomicInteger(0);

    // volatile不能保证原子性，这个方法不保证线程安全,下方使用了join，起到了线程安全效果
    public static void increase1() {
        m++;
    }

    public static void increase2() {
        atomicInteger.incrementAndGet();
    }

    public static void main(String[] args) throws InterruptedException {
        Thread[] t1 = new Thread[50];
        for (Thread t : t1) {
            t = new Thread(() -> {
                CAS1.increase1();
            });
            t.start();
            // 等待此线程执行完毕,根据happens-before原则，调用join之后，可以看到其他线程的修改，此处没有并发问题
            t.join();
        }
        System.out.println(m);

        Thread[] t2 = new Thread[50];
        for (Thread t : t2) {
            t = new Thread(() -> {
                CAS1.increase2();
            });
            t.start();
            t.join();   // 等待此线程执行完毕
        }
        System.out.println("原子结果:" + atomicInteger.get());
    }
}
