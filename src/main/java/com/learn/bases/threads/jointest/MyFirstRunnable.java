package com.learn.bases.threads.jointest;

public class MyFirstRunnable implements Runnable{
    @Override
    public void run() {
        try {
            Thread.currentThread().sleep(100l);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("In a thread " + Thread.currentThread().getName());
    }
}