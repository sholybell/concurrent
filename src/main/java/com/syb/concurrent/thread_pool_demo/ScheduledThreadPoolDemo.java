package com.syb.concurrent.thread_pool_demo;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class ScheduledThreadPoolDemo {

    public static void main(String[] args) {
        ScheduledExecutorService pool = Executors.newScheduledThreadPool(5);
        // 创建10个任务给pool
        for (int i = 0; i < 10; i++) {
            Runnable task = new TaskDemo();
            // 把任务交给线程池
            pool.execute(task);
        }

        // 关闭线程池
        pool.shutdown();
    }
}
