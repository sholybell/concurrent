package com.syb.concurrent.thread_pool_demo;

import java.util.concurrent.TimeUnit;

public class TaskDemo implements Runnable{


    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName()+ " is running");
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
