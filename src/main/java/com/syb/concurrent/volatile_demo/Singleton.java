package com.syb.concurrent.volatile_demo;

/**
 * 单例模式，双重检测机制
 */
public class Singleton {

    private volatile static Singleton instance;

    private Singleton() {       // 单例模式不要忘记了私有化构造函数
    }

    public static Singleton getInstance() {
        if (instance == null) {
            synchronized (Singleton.class) {
                if (instance == null) {         // 第二次校验避免两个线程通过第一次校验，同时结合volatile属性，保证第二个获取到锁的线程可以看到前一个线程创建的单例对象，
                    instance = new Singleton(); // 否则可能由于指令重排，第二个线程可能看到自己本地线程内存的instance引用的是null。(理论上，难以重现)
                }
            }
        }
        return instance;
    }

}
