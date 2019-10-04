package com.melo.lock;

/**
 * Created by 76009 on 2019/10/3.
 */
public class SyncAccount {

    private int balance;

    private Object lock;

    private SyncAccount() {

    }

    public SyncAccount(Object lock) {
        this.lock = lock;
    }

    public void transfer(SyncAccount target, int money) {
        synchronized (lock) {
            if (this.balance > money) {
                this.balance -= money;
                target.balance += money;
            }
        }
    }
}
