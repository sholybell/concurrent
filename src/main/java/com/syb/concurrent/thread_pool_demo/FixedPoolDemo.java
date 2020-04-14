package com.syb.concurrent.thread_pool_demo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FixedPoolDemo {

    public static void main(String[] args) {
        // 创建固定大小的线程池，这个线程池如果里面的线程抛出异常，会重建一个新的线程，执行新的任务
        ExecutorService pool = Executors.newFixedThreadPool(5);
        // 创建10个任务给pool
        for (int i = 0; i < 10; i++) {
            Runnable task = new TaskDemo();
            // 把任务交给线程池
            pool.execute(task);
        }

        // 关闭线程池
        pool.shutdown();
        // 如果要确保线程池结束再往后执行，可以这么做
        while (!pool.isTerminated()) {

        }
        System.out.println("线程池消亡");
    }
}
