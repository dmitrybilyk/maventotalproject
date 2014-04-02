package com.learn.patterns.structural.adapter;

/**
 * Created with IntelliJ IDEA.
 * User: dmitriy.bilyk
 * Date: 12.11.13
 * Time: 20:19
 * To change this template use File | Settings | File Templates.
 */
import java.util.ArrayList;

//Adapter. defines existing interface that needs adapting
public class PrintableListAdapter implements PrintableList{

    public void printList(ArrayList<String> list) {

        //Converting ArrayList<String> to String so that we can pass String to
        // adaptee class
        String listString = "";

        for (String s : list)
        {
            listString += s + "\t";
        }

        // instantiating adaptee class
        PrintString printString=new PrintString();
        printString.print(listString);
    }
}
