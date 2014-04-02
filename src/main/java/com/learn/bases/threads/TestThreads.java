package com.learn.bases.threads;

/**
 * Created by IntelliJ IDEA.
 * User: bilyk
 * Date: 02.07.12
 * Time: 18:12
 * To change this template use File | Settings | File Templates.
 */
public class TestThreads {

        public static void main (String args [] )
        {
            new StringThread("Java",50 );
        }


    static class StringThread implements Runnable
    {
        private String str;
        private int num;

        StringThread(String s, int n)
        {
            str=new String (s);
            num=n;
            Thread t=new Thread (this);
            t. start ( );
        }

        public void run ()
        {
            for (int i=1; i<=num; i++)
                System.out.print (str+" ");
        }
}}
