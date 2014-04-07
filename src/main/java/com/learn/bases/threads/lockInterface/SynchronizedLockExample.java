package com.learn.bases.threads.lockInterface;

public class SynchronizedLockExample implements Runnable{
 
    private Resource resource;
     
    public SynchronizedLockExample(Resource r){
        this.resource = r;
    }
     
    @Override
    public void run() {
        synchronized (resource) {
            resource.doSomething();
        }
        resource.doLogging();
    }
}

//Notice that I am using synchronized block to acquire the lock on Resource object.
// We could have created a dummy object in the class and used that for locking purpose.