package com.learn.bases.threads.wicht;

public class Main {
    public static void main(String[] args) {
        MyRunnableForJoinTest myRunnableForJoinTest = new MyRunnableForJoinTest();
        MyFirstRunnable target = new MyFirstRunnable();
        for (int i = 0; i <20; i++) {

            Thread thread = new Thread(target);

            thread.start();
            if(i == 10){
                System.out.println(thread.getName());
                thread.interrupt();
            }
        }
        System.out.println("in Main thread "+ Thread.currentThread().getName());
    }
}
