package com.syb.concurrent.aqs_demo;

import java.util.concurrent.TimeUnit;

/**
 * 这里模拟多线程顺序增加，出现线程不安全的问题
 */
public class Demo01 {

    private int m = 0;

    public int next() {
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            throw new RuntimeException("ERROR");
        }
        return m++;
    }

    public static void main(String[] args) {
        Demo01 demo = new Demo01();
        Thread[] th = new Thread[20];
        for (int i = 0; i < 20; i++) {
            th[i] = new Thread(() -> {
                System.out.println(demo.next());
            });
            th[i].start();
        }
    }
}
