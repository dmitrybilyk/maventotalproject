package com.learn.bases.threads;

/**
 * Created by IntelliJ IDEA.
 * User: bilyk
 * Date: 02.07.12
 * Time: 18:12
 * To change this template use File | Settings | File Templates.
 */
class StringThread implements Runnable
{
    private String str;
    private int num;

    StringThread(String s, int n)
    {
        str  = new String (s);
        num =n;
    }

    public void run ( )
    {
        for (int i=1; i<=num; i++)
            System.out.print (str+" ");
    }
}
