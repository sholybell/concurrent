package com.syb.concurrent.aqs_demo;


public class Demo03 {

    private MyLock lock = new MyLock();

    private int m = 0;

    /**
     * 这里递归调用，使用了lock这把锁，但是这里并不是可重入的!!!!
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
        Demo03 demo = new Demo03();
        new Thread(() -> {
            demo.a();
        }).start();
    }
}
