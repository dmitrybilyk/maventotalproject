//package com.learn.bases.threads.threadsafety.synchronization;
//
//public class MyObject {
//
//    // Locks on the object's monitor
//    public synchronized void doSomething() {
//        // ...
//    }
//}
//
//// Hackers code
//MyObject myObject = new MyObject();
//synchronized (myObject) {
//        while (true) {
//        // Indefinitely delay myObject
//        Thread.sleep(Integer.MAX_VALUE);
//        }
//        }