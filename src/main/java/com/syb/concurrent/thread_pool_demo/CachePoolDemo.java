package com.syb.concurrent.thread_pool_demo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CachePoolDemo {

    public static void main(String[] args) {
        // 可变线程池数量的线程池，这要注意任务量大，线程数会很多
        ExecutorService pool = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            Runnable task = new TaskDemo();
            // 把任务交给线程池
            pool.execute(task);
        }

        pool.shutdown();
    }
}
