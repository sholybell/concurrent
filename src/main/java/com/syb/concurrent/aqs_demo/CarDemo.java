package com.syb.concurrent.aqs_demo;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * 使用{@link java.util.concurrent.Semaphore}模拟停车场
 */
public class CarDemo {
    public static void main(String[] args) {
        //创建Semaphore
        Semaphore sp = new Semaphore(5);

        Thread[] car = new Thread[10];
        for (int i = 0; i < 10; i++) {

            car[i] = new Thread(() -> {
                //请求许可
                try {
                    TimeUnit.SECONDS.sleep(2);
                    sp.acquire();
                    System.out.println(Thread.currentThread().getName() + "可以进入停车场");

                    //使用资源
                    TimeUnit.SECONDS.sleep(new Random().nextInt(10));

                    //离开（释放资源）
                    sp.release();
                    System.out.println(Thread.currentThread().getName() + "离开停车场");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }, "car[" + i + "]");
            car[i].start();
        }
    }
}
