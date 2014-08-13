package com.learn.exercises;

class MyThread2 extends Thread
{
    public static void main(String [] args) 
    {
        MyThread2 t = new MyThread2();
        t.start();
        System.out.print("one. ");
        t.start();
        System.out.print("two. ");
    }
    public void run() 
    {
        System.out.print("Thread ");
    }
}

//When the start() method is attempted a second time on a single Thread object, the method will
// throw an IllegalThreadStateException (you will not need to know this exception name for the exam).
// Even if the thread has finished running, it is still illegal to call start() again.