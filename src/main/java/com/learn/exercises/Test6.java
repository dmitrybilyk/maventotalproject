package com.learn.exercises;

class A
{
    final public int GetResult(int a, int b) { return 0; }
} 
class B extends A 
{ 
//    public int GetResult(int a, int b) {return 1; }
} 
public class Test6
{
    public static void main(String args[]) 
    { 
        B b = new B(); 
        System.out.println("x = " + b.GetResult(0, 1));  
    } 
}

//The code doesn't compile because the method GetResult() in class A is final and so cannot be overridden.
