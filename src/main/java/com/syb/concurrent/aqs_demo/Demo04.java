package com.syb.concurrent.aqs_demo;


public class Demo04 {

    /**
     * 对应JDK的实现{@link java.util.concurrent.locks.ReentrantLock}
     */
    private MyLock02 lock = new MyLock02();

    private int m = 0;

    /**
     * 这里的锁是可重入的!!!
     */
    public void a() {
        lock.lock();
        System.out.println("a");
        b();
        lock.unlock();
    }

    public void b() {
        lock.lock();
        System.out.println("b");
        lock.unlock();
    }

    public static void main(String[] args) {
        Demo04 demo = new Demo04();
        new Thread(() -> {
            demo.a();
        }).start();
    }
}
