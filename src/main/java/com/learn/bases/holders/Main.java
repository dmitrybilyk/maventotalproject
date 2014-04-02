package com.learn.bases.holders;

import org.omg.CORBA.BooleanHolder;

/**
 * Created with IntelliJ IDEA.
 * User: dmitriy.bilyk
 * Date: 14.01.14
 * Time: 22:43
 * To change this template use File | Settings | File Templates.
 */
public class Main {
    public static void main(String[] args) {
        BooleanHolder b = new BooleanHolder(true);
        Boolean bb = new Boolean(true);
        ToDoWithBoolean toDoWithBoolean = new ToDoWithBoolean();
        toDoWithBoolean.toDoWithBoolean(b, bb);
        System.out.println(b.value);
        System.out.println(bb);
    }
}

class ToDoWithBoolean{
    public void toDoWithBoolean(BooleanHolder booleanHolder, Boolean b){
        b = false;
        booleanHolder.value = false;
    }
}
