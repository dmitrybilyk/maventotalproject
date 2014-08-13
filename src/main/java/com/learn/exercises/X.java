package com.learn.exercises;

class X implements Runnable
{ 
    public static void main(String args[]) 
    {
        X run = new X(); Thread t = new Thread(run); t.start();
    } 
    public void run() {
        System.out.println("Something happend here");
    }
}