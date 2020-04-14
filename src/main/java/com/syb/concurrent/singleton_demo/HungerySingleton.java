package com.syb.concurrent.singleton_demo;

public class HungerySingleton {

    //加载的时候就产生的实例对象,JVM保证加载时单线程的，线程安全
    private static HungerySingleton instance = new HungerySingleton();

    private HungerySingleton() {
    }

    //返回实例对象
    public static HungerySingleton getInstance() {
        return instance;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 20; i++) {
            new Thread(() -> {
                System.out.println(HungerySingleton.getInstance());
            }).start();
        }
    }
}