package com.learn.bases.threads.jointest;

public class Main {
    public static void main(String[] args) {
        MyRunnableForJoinTest myRunnableForJoinTest = new MyRunnableForJoinTest();
        MyFirstRunnable target = new MyFirstRunnable();
        for (int i = 0; i <20; i++) {

            Thread thread = new Thread(target);

            thread.start();

            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if(i == 10){
                thread.interrupt();
                System.out.println(thread.getName());
            }
        }
        System.out.println("in Main thread "+ Thread.currentThread().getName());
    }
}
