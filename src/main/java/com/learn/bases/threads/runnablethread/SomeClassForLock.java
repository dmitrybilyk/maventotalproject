package com.learn.bases.threads.runnablethread;

/**
 * Created by dmitry.bilyk on 4/3/14.
 */
public class SomeClassForLock {
    private static int balance = 10;
    public synchronized void plus(int plus){
//        try {
            System.out.println("The name of current thread in plus - " + Thread.currentThread().getName());
//            Thread.currentThread().sleep(5000l);
            Thread.currentThread().yield();

//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        balance += plus;
        System.out.println("The total balance = " + getBalance());

//        System.out.println("The sum after plus = " + getBalance());
    }
    public void withdraw(int minus){
        balance -= minus;
    }

    public int getBalance() {
        return balance;
    }
}
