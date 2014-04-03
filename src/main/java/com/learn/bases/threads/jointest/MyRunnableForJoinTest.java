package com.learn.bases.threads.jointest;

public class MyRunnableForJoinTest implements Runnable{
    @Override
    public void run() {
        System.out.println("First 10 died and now I'm showing this message!!!");
    }
}
