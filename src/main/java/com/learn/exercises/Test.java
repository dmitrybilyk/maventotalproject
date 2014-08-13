package com.learn.exercises;

public class Test
{
    public static int y;
    public static void foo(int x) 
    {
        System.out.print("foo ");
        y = x;
    }
    public static int bar(int z) 
    {
        System.out.print("bar ");
        return y = z;
    }
    public static void main(String [] args ) 
    {
        int t = 0;
        assert t > 0 : bar(7);
//        assert t > 1 : foo(8); /* Line 18 */ //
        System.out.println("done ");
    }
}

//    The foo() method returns void. It is a perfectly acceptable method,
// but because it returns void it cannot be used in an assert statement,
// so line 18 will not compile.