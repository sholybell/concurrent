package com.syb.concurrent.aqs_demo;

/**
 * 使用自定义的锁，实现线程安全的自增
 */
public class Demo02 {

    private MyLock lock = new MyLock();

    private int m = 0;

    public int next() {
        lock.lock();
        try {
            return m++;
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        Demo02 demo = new Demo02();
        Thread[] th = new Thread[20];
        for (int i = 0; i < 20; i++) {
            th[i] = new Thread(() -> {
                System.out.println(demo.next());
            });
            th[i].start();
        }
    }
}
