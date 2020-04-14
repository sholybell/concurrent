package com.syb.concurrent.volatile_demo;

import java.util.concurrent.CountDownLatch;

public class IncreaseDemo {

    static volatile int m = 0;

    public static void increase() {
        m++;        // ++操作不具备原子性，volatile不能保证原子性
    }

    /**
     * 这里不能使用CountDownLatch，CountDownLatch底层会令多线程操作m这个变量符合happens-before的可见性，线程安全
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {

//        CountDownLatch latch = new CountDownLatch(50);
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                for (int j = 0; j < 5; j++) {
                    increase();
//                    latch.countDown();
                }
            }).start();
        }
//        latch.await();  // 保证线程执行完毕
        System.out.println(m);
    }
}
