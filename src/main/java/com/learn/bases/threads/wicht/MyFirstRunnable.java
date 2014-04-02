package com.learn.bases.threads.wicht;

public class MyFirstRunnable implements Runnable{
    @Override
    public void run() {
        System.out.println("In a thread " + Thread.currentThread().getName());
    }
}