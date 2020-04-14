package com.syb.concurrent.singleton_demo;

public class LazyDCLSingleton {

    private Integer num;
    private String str;

    private volatile static LazyDCLSingleton instance = null;   // 双重检测的单例模式，及主要结合volatile关键字

    private LazyDCLSingleton() {
        this.num = 1;
        this.str = "1";
    }

    public static LazyDCLSingleton getInstance() {
        if (null == instance)           //  这里有可能会造成单例对象的成员属性还没有初始化完成，但是单例对象不为null，直接返回给线程处理
            synchronized (LazyDCLSingleton.class) {
                if (null == instance)
                    instance = new LazyDCLSingleton();
            }
        return instance;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 20; i++) {
            new Thread(() -> {
                System.out.println(LazyDCLSingleton.getInstance());
            }).start();
        }
    }
}