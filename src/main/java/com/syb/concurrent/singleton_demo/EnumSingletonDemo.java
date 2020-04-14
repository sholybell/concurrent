package com.syb.concurrent.singleton_demo;

public class EnumSingletonDemo {

    private EnumSingletonDemo() {
    }

    //延迟加载
    private enum EnumHolder {
        INSTANCE;
        private EnumSingletonDemo instance = null;

        private EnumHolder() {
            instance = new EnumSingletonDemo();
        }
    }

    //懒加载
    public static EnumSingletonDemo getInstance() {
        return EnumHolder.INSTANCE.instance;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 50; i++) {
            new Thread(() -> {
                System.out.println(EnumSingletonDemo.getInstance());
            }).start();
        }
    }
}