package com.syb.concurrent.aqs_demo;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

/**
 * 使用赛跑的例子，举例{@link CyclicBarrier}
 */
public class RaceDemo {

    public static void main(String[] args) {
        CyclicBarrier barrier = new CyclicBarrier(8);
        Thread[] player = new Thread[8];
        for (int i = 0; i < 8; i++) {
            player[i] = new Thread(() -> {
                try {
                    TimeUnit.SECONDS.sleep(2);
                    System.out.println(Thread.currentThread().getName() + "准备好了 ");
                    barrier.await();    // 先到这里的线程都等待,所有的线程都到达了，再去执行线程下面的逻辑
                    // 这个用法挺新颖的，以前想着和CountDownLatch的countDown一样放在子线程的逻辑最后了
                } catch (BrokenBarrierException | InterruptedException e) {
                    e.printStackTrace();
                }
                // 这里的逻辑会等到上面的线程都执行到await,才会都开始执行这行逻辑
                System.out.println("选手" + Thread.currentThread().getName() + "起跑");
            }, "player[" + i + "]");
            player[i].start();

        }
    }
}
