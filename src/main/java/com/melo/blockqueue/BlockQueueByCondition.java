package com.melo.blockqueue;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 通过条件变量实现阻塞队列
 * Created by 76009 on 2019/10/5.
 */
public class BlockQueueByCondition<T> {

    final Lock lock = new ReentrantLock();

    final Condition notFull = lock.newCondition();
    final Condition notEmpty = lock.newCondition();

    boolean full = true;
    void enqueue(T x) throws InterruptedException {
        lock.lock();

        try {
            while (full) {
                notFull.await();
                //入队操作
            }

            notFull.signal();
        } finally {
            lock.unlock();
        }
    }

    boolean empty = true;
    void dequeue(T y) throws InterruptedException {
        lock.lock();

        try {
            while (empty) {
                notEmpty.await();
                //出队操作
            }
            notEmpty.signal();
        } finally {
            lock.unlock();
        }
    }

}
