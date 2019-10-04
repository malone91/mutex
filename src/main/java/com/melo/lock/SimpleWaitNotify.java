package com.melo.lock;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * Created by 76009 on 2019/10/4.
 */
public class SimpleWaitNotify {

    public static void main(String[] args) throws InterruptedException {
        Account src = new Account(10000);
        Account tag = new Account(10000);

        CountDownLatch countDownLatch = new CountDownLatch(9999);
        for (int i = 0; i < 9999; i++) {
            new Thread(() -> {
                src.transactionToTarget(1, tag);
                countDownLatch.countDown();
            }).start();
        }
        countDownLatch.await();

        System.out.println(src.getBalance());
        System.out.println(tag.getBalance());
    }

    /**
     * 账户类
     */
    static class Account {
        private Integer balance;

        public Integer getBalance() {
            return balance;
        }

        public void setBalance(Integer balance) {
            this.balance = balance;
        }

        public Account(Integer balance) {
            this.balance = balance;
        }

        public void transactionToTarget(Integer money, Account target) {
            //lock
            Allocator.getInstance().apply(this, target);

            this.balance -= money;
            target.setBalance(target.getBalance() + money);

            //unlock
            Allocator.getInstance().release(this, target);
        }
    }

    /**
     * 锁 单利
     */
    static class Allocator {
        private Allocator() {}

        private List<Account> locks = new ArrayList<>();

        public synchronized void apply(Account src, Account tag) {
            while (locks.contains(src) || locks.contains(tag)) {
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            locks.add(src);
            locks.add(tag);
        }

        public synchronized void release(Account src, Account tag) {
            locks.remove(src);
            locks.remove(tag);

            this.notifyAll();
        }

        public static Allocator getInstance() {
            return AllocatorSingle.instance;
        }

        static class AllocatorSingle {
            public static Allocator instance = new Allocator();
        }
    }
}
