package com.syb.concurrent.concurrent_collection_demo;

import java.util.Date;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * 举例线程安全的阻塞并发容器
 */
public class LinkedBlockingDequeDemo {

    public static void main(String[] args) throws InterruptedException {
        LinkedBlockingDeque<String> list = new LinkedBlockingDeque<>(3);
        Thread thread = new Thread(() -> {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 5; j++) {
                    String str = new String(i + ":" + j);
                    try {
                        list.put(str);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("client:" + str + (new Date()));
                }
            }
        });
        thread.start();

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 3; j++) {
                String str = list.take();
                System.out.println("main:take" + str + " size:" + list.size());
            }
        }
    }
}
