package com.learn.bases.collections;

import java.util.Iterator;
import java.util.Stack;

/**
 * Created with IntelliJ IDEA.
 * User: Buh
 * Date: 19.08.12
 * Time: 14:04
 * To change this template use File | Settings | File Templates.
 */
public class StackExam {

    public static void main(String[] args) {

        Stack<String> sk=new Stack<String>();

        sk.push("a");
        sk.push("c");
        sk.push("e");
        sk.push("d");

        Iterator it=sk.iterator();

        System.out.println("Size before pop() :"+sk.size());

        while(it.hasNext())
        {
            String iValue=(String)it.next();
            System.out.println("Iterator value :"+iValue);
        }

        // get and remove last element from stack
        String value =(String)sk.pop();

        System.out.println("value :"+value);

        System.out.println("Size After pop() :"+sk.size());

    }
}
