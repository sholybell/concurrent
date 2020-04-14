package com.syb.concurrent.singleton_demo;

public class LazySingleton {
    private static LazySingleton instance = null;

    private LazySingleton() {
    }

    public static LazySingleton getInstance() {
        if (null == instance)
            instance = new LazySingleton();
        return instance;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 20; i++) {
            new Thread(() -> {
                System.out.println(LazySingleton.getInstance());    // 线程不安全，可能获取到不同的对象
            }).start();
        }
    }
}