package com.learn.bases.threads.runnablethread;

public class RunnableThread implements Runnable{
    SomeClassForLock someClassForLock = new SomeClassForLock();
    Thread runner;
    public RunnableThread() {
    }
    public RunnableThread(String threadName) throws InterruptedException {
        runner = new Thread(this, threadName); // (1) Create a new thread.
        System.out.println(runner.getName());
        runner.start(); // (2) Start the thread.
    }
    public void run() {
        //Display info about this particular thread
//        System.out.println(Thread.currentThread());
        someClassForLock.plus(10);
    }
}
