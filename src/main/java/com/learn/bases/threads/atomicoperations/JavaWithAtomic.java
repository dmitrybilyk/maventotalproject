package com.learn.bases.threads.atomicoperations;

import java.util.concurrent.atomic.AtomicInteger;
 

//Benefits of using Atomic Concurrency classes is that we don’t need to worry
// about synchronization at each and every place we are dealing with integers
// and it’s assumed to be more efficient that synchronization which involves
// locking resources.

public class JavaWithAtomic {
 
    public static void main(String[] args) throws InterruptedException {
 
        ProcessingWithAtThread pt = new ProcessingWithAtThread();
        Thread t1 = new Thread(pt, "t1");
        t1.start();
        Thread t2 = new Thread(pt, "t2");
        t2.start();
        t1.join();
        t2.join();
        System.out.println("Processing count=" + pt.getCount());
    }
 
}
 
 
class ProcessingWithAtThread implements Runnable {
    private AtomicInteger count = new AtomicInteger();
 
 
    @Override
    public void run() {
        for (int i = 1; i < 5; i++) {
            processSomething(i);
            count.incrementAndGet();
        }
    }
 
 
    public int getCount() {
        return this.count.get();
    }
 
 
    private void processSomething(int i) {
        // processing some job
        try {
            Thread.sleep(i * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
 
}