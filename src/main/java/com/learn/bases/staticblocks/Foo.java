package com.learn.bases.staticblocks;

public class Foo
{
    public static final int widgets;

    static
    {
        int first = 2;
        int second = 3;
        // Imagine more complex logic here which really used first/second
        widgets = first + second;
    }
}
