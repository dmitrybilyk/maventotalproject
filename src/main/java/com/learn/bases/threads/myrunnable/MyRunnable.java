package com.learn.bases.threads.myrunnable;

/**
 * Created with IntelliJ IDEA.
 * User: dmitriy.bilyk
 * Date: 31.03.13
 * Time: 21:35
 * To change this template use File | Settings | File Templates.
 */
public class MyRunnable implements Runnable {
    private final long countUntil;

    MyRunnable(long countUntil) {
        this.countUntil = countUntil;
    }

    @Override
    public void run() {
        long sum = 0;
        for (long i = 1; i < countUntil; i++) {
            sum += i;
        }
        System.out.println(sum);
    }
}
