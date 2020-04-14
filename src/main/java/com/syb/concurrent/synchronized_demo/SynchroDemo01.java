package com.syb.concurrent.synchronized_demo;

import java.util.concurrent.TimeUnit;

/**
 * 对象锁和类锁是不互斥的
 */
public class SynchroDemo01 {

    /**
     * 修饰静态方法
     */
    public synchronized static void accessResouces0() {
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " is running");
    }

    /**
     * 修饰非静态方法
     */
    public synchronized void accessResouces1() {
        try {
            TimeUnit.MINUTES.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " is running");
    }

    /**
     * 这个方法和accessResouces1作用相同，他们用的同一把锁(表示他们并发时会串行)
     */
    public void accessResouces2() {
        synchronized (this) {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName() + " is running");
    }

    /**
     * 这个方法和accessResouces0作用相同，他们用的同一把锁(表示他们并发时会串行)
     */
    public void accessResouces3() {
        synchronized (SynchroDemo01.class) {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName() + " is running");
    }

    public static void main(String[] args) {
        SynchroDemo01 demo01 = new SynchroDemo01();
        for (int i = 0; i < 5; i++) {
            new Thread(demo01::accessResouces1).start();
        }
    }
}
