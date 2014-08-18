package com.learn.bases.threads.jointest;

public class JoinThread {
    public static void main(String[] args) {
//        Thread thread2 = new Thread(new WaitRunnable());
        Thread thread3 = new Thread(new WaitRunnable());

//        System.out.println("Current time millis first: " + System.currentTimeMillis());

//        thread2.start();

//        try {
//            thread2.join();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        System.out.println("Current time millis second: " + System.currentTimeMillis());

        thread3.start();

        try {
            thread3.join(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Current time millis third: " + System.currentTimeMillis());
    }

    private static class WaitRunnable implements Runnable {
        @Override
        public void run() {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}