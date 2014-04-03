package com.learn.bases.threads.runnablethread;

public class RunnableExample {
    public static void main(String[] args) throws InterruptedException {
        SomeClassForLock someClassForLock = new SomeClassForLock();


//        Thread thread1 = new Thread(new RunnableThread(), "thread1");
//        Thread thread2 = new Thread(new RunnableThread(), "thread2");
        Thread threadToChecksyncMethod = new Thread(new RunnableThread(), "theadToCheckSyncMethod");
//        RunnableThread thread3 = new RunnableThread("thread3");
        //Start the threads
//        thread1.start();
//        thread2.start();
        try {

            synchronized (someClassForLock){
                someClassForLock.plus(20);
            }

            threadToChecksyncMethod.start();
            //delay for one second
            Thread.currentThread().sleep(1000);
        } catch (InterruptedException e) {
        }
        //Display info about the main thread
//        System.out.println(Thread.currentThread());

    }
}
