package com.learn.bases.threads.threadpools.kumar;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;


//we can limit the size of thread pool and create our own RejectedExecutionHandler
// implementation to handle the jobs that can’t fit in the worker queue
public class RejectedExecutionHandlerImpl implements RejectedExecutionHandler {
 
    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        System.out.println(r.toString() + " is rejected");
    }
 
}