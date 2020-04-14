package com.syb.concurrent.volatile_demo;

import java.util.concurrent.TimeUnit;

public class ReaderAndUpdater {

    final static int MAX = 50;
    static volatile int init_value = 0;     // 如果这边没有使用volatile，Reader的线程感知不到Updater线程的修改(其实也是可以看到的，不过不能保证看到的时机)

    public static void main(String[] args) {
        new Thread(() -> {
            int localValue = init_value;
            while (localValue < MAX) {
                if (localValue != init_value) {
                    System.out.println("Reader:" + init_value);
                    localValue = init_value;
                }
            }
        }, "Reader").start();

        new Thread(() -> {
            int localValue = init_value;
            while (localValue < MAX) {
                System.out.println("updater:" + (++localValue));
                init_value = localValue;
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "Updater").start();
    }
}
