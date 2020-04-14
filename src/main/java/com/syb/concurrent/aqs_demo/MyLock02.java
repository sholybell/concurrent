package com.syb.concurrent.aqs_demo;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * 实现可重入锁的性质
 */
public class MyLock02 implements Lock {

    private Helper helper = new Helper();

    private class Helper extends AbstractQueuedSynchronizer {
        //获取锁
        @Override
        protected boolean tryAcquire(int arg) {
            int state = getState();
            if (state == 0) {
                //利用CAS原理修改state
                if (compareAndSetState(0, arg)) {
                    //设置当前线程占有资源
                    setExclusiveOwnerThread(Thread.currentThread());
                    return true;
                }
            } else if (getExclusiveOwnerThread() == Thread.currentThread()) {   // 实现可重入锁的特性
                setState(getState() + arg);
                return true;
            }
            return false;
        }

        //释放锁
        @Override
        protected boolean tryRelease(int arg) {
            int state = getState() - arg;
            //判断释放后是否为0
            if (state == 0) {
                setExclusiveOwnerThread(null);
                setState(state);
                return true;
            }
            setState(state);    // 这里不需要使用CAS，没有线程安全问题，因为要释放独占锁的时候，表示只有当前线程持有锁，释放锁并不会发生竞争问题!!!
            return false;
        }

        public Condition newConditionObject() {
            return new ConditionObject();
        }
    }

    @Override
    public void lock() {
        helper.acquire(1);
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
        helper.acquireInterruptibly(1);
    }

    @Override
    public boolean tryLock() {
        return helper.tryAcquire(1);
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return helper.tryAcquireNanos(1, unit.toNanos(time));
    }

    @Override
    public void unlock() {
        helper.release(1);
    }

    @Override
    public Condition newCondition() {
        return helper.newConditionObject();
    }
}
