package com.syb.concurrent.volatile_demo;

/**
 * 开关模式
 * 这可以令某个线程不退出，然后提供某个修改started变量的方法，从而推出
 */
public class ShutDowsnDemmo extends Thread {

    private volatile boolean started = false;

    @Override
    public void run() {
        while (started) {
            // 这里可以处理关闭某线程的逻辑
        }
    }

    public void shutdown() {
        started = false;
    }
}

