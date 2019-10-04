package com.melo.lock;

import java.util.List;

/**
 * Created by 76009 on 2019/10/3.
 */
public class Allocator {

    private List<Object> als;

    synchronized void apply(Object from, Object to) {
        while (als.contains(from) || als.contains(to)) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        als.add(from);
        als.add(to);
    }

    synchronized void free(Object from, Object to) {
        als.remove(from);
        als.remove(to);
        notifyAll();
    }

}
