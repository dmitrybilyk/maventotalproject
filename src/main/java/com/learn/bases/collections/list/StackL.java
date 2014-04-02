package com.learn.bases.collections.list;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: Buh
 * Date: 19.08.12
 * Time: 16:50
 * To change this template use File | Settings | File Templates.
 */
public class StackL implements Comparator{

    private LinkedList<Integer> list = new LinkedList<Integer>();

    public void push(Object v) {
            list.addFirst((Integer) v);
    }

    public Object top() {
        return list.getFirst();
    }

    public Object pop() {
        return list.removeFirst();
    }

    public static void main(String[] args) {
        Stack stack = new Stack();
//        for (int i = 0; i < 10; i++)
            stack.push(new Integer(5));
            stack.push(new Integer(7));
            stack.push(new Integer(3));
            stack.push(new Integer(6));
            stack.push(new Integer(5));
            stack.push(new Integer(8));
            LinkedList<Integer> linkedList = new LinkedList<Integer>();
            linkedList.addAll((List<? extends Integer>) stack);
    }

    @Override
    public int compare(Object o1, Object o2) {
        return 0;
    }
} ///:~