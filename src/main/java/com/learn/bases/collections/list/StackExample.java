package com.learn.bases.collections.list;

/**
 * Created with IntelliJ IDEA.
 * User: Buh
 * Date: 19.08.12
 * Time: 14:54
 * To change this template use File | Settings | File Templates.
 */
import java.util.Collections;
import java.util.Comparator;
import java.util.Stack;
import java.util.EmptyStackException;
public class StackExample
{
    static void showpush(Stack st, int a)
    {
        st.push(new Integer(a));
        System.out.println("push(" + a + ")");
        System.out.println("stack: " + st);
    }
    static void showpop(Stack st)
    {
        System.out.print("pop -> ");
        Integer a = (Integer) st.pop();
        System.out.println(a);
        System.out.println("stack: " + st);
    }
    public static void main(String args[])
    {
        Stack st = new Stack();
        System.out.println("stack: " + st);
        showpush(st, 42);
        showpush(st, 66);
        showpush(st, 99);
/*        showpop(st);
        showpop(st);
        showpop(st);*/

        System.out.println("dsfads "+Collections.max(st, new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                return 0;  //To change body of implemented methods use File | Settings | File Templates.
            }
        }));
        try
        {
            showpop(st);
        }
        catch (EmptyStackException e)
        {
            System.out.println("empty stack");
        }
    }
}
