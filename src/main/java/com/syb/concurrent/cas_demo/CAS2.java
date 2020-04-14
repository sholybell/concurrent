package com.syb.concurrent.cas_demo;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * 这里模拟CAS操作中ABA的问题，为了避免ABA的问题可以使用CAS提供的
 * {@link java.util.concurrent.atomic.AtomicStampedReference}
 */
public class CAS2 {

    private static AtomicStampedReference<Integer> as1 = new AtomicStampedReference(100, 1);

    public static void main(String[] args) {
//        Thread t1 = new Thread(() -> {
//            atomicInteger.compareAndSet(100, 110);
//            System.out.println(atomicInteger.get());
//        });
//        t1.start();
//
//        Thread t2 = new Thread(() -> {
//            try {
//                TimeUnit.SECONDS.sleep(2);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            atomicInteger.compareAndSet(110, 100);
//            System.out.println(atomicInteger.get());
//        });
//        t2.start();
//
//
//        Thread t3 = new Thread(() -> {
//            try {
//                TimeUnit.SECONDS.sleep(3);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            atomicInteger.compareAndSet(100, 120);
//            System.out.println(atomicInteger.get());
//        });
//        t3.start();


        System.out.println("-----------------版本号处理ABA问题------------------>");


        Thread t4 = new Thread(() -> {
            System.out.println(as1.compareAndSet(100, 110, as1.getStamp(), as1.getStamp() + 1));
            System.out.println(as1.compareAndSet(110, 100, as1.getStamp(), as1.getStamp() + 1));
        });
        t4.start();

        Thread t5 = new Thread(() -> {
            int stamp = as1.getStamp();     // t4和t5可能瞬间并发，此时获取到的可能是旧的stamp，t5先获取，t4后面会修改
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(as1.compareAndSet(110, 120, stamp, stamp + 1));
        });
        t5.start();

    }


}
