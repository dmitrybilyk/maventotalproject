package com.learn.bases.castingtest;

/**
 * Created by IntelliJ IDEA.
 * User: bilyk
 * Date: 27.06.12
 * Time: 17:16
 * To change this template use File | Settings | File Templates.
 */
public class Conversion {
    public static void main(String[] args){
        boolean t = true;
        byte b = 2;
        short s = 100;
        char c = 'C';
        int i = 200;
        long l = 24000;
        float f = 3.14f;
        double d = 0.000000000000053;
        String g = "string";
        System.out.println("Value of all the variables like");
        System.out.println("t = " + t );
        System.out.println("b = " + b );
        System.out.println("s = " + s );
        System.out.println("c = " + c );
        System.out.println("i = " + i );
        System.out.println("l = " + l );
        System.out.println("f = " + f );
        System.out.println("d = " + d );
        System.out.println("g = " + g );
        System.out.println();
        //Convert from boolean to byte.
        b = (byte)(t?1:0);
        System.out.println("Value of b after conversion : " + b);
        //Convert from boolean to short.
        s = (short)(t?1:0);
        System.out.println("Value of s after conversion : " + s);
        //Convert from boolean to int.
        i = (int)(t?1:0);
        System.out.println("Value of i after conversion : " + i);
        //Convert from boolean to char.
        c = (char)(t?'1':'0');
        System.out.println("Value of c after conversion : " + c);
        c = (char)(t?1:0);
        System.out.println("Value of c after conversion in unicode : " + c);
        //Convert from boolean to long.
        l = (long)(t?1:0);
        System.out.println("Value of l after conversion : " + l);
        //Convert from boolean to float.
        f = (float)(t?1:0);
        System.out.println("Value of f after conversion : " + f);
        //Convert from boolean to double.
        d = (double)(t?1:0);
        System.out.println("Value of d after conversion : " + d);
        //Convert from boolean to String.
        g = String.valueOf(t);
        System.out.println("Value of g after conversion : " + g);
        g = (String)(t?"1":"0");
        System.out.println("Value of g after conversion : " + g);
        int sum = (int)(b + i + l + d + f);
        System.out.println("Value of sum after conversion : " + sum);
    }
}
