package com.melo.sync;

/**
 * Created by 76009 on 2019/10/2.
 */
public class CountTest {

    private long count = 0;

    private void add10k() {
        int index = 0;
        while (index++ < 10000) {
            count++;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        final CountTest test = new CountTest();

        Thread thread1 = new Thread(()->{
            test.add10k();
        });

        Thread thread2 = new Thread(()->{
            test.add10k();
        });

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.println(test.count);
    }
}
