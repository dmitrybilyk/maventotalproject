package com.learn.bases.threads.threadpools.kumar.simple;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
 
public class SimpleThreadPoolTest {
 
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 7; i++) {
            Runnable worker = new SimpleWorkerThread("" + i);
            executor.execute(worker);
          }
        executor.shutdown();
        while (!executor.isTerminated()) {
        }
        System.out.println("Finished all threads");
    }

//    In above program, we are creating fixed size thread pool of 5 worker threads.
// Then we are submitting 10 jobs to this pool, since the pool size is 5,
// it will start working on 5 jobs and other jobs will be in wait state,
// as soon as one of the job is finished, another job from the wait queue
// will be picked up by worker thread and get’s executed.

}