package com.learn.bases.collections;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by dmitry on 1/27/14.
 */
public class ArrayListVsLinkedList {
    public static void main(String[] args) {
//        List<String> arrayList = new ArrayList<String>();
        LinkedList<String> arrayList = new LinkedList<String>();
        arrayList.add("a");
        arrayList.add("b");
        arrayList.add("c");
        arrayList.add("d");
        arrayList.add("e");
        arrayList.push("pushed");

        for (String s : arrayList) {
            System.out.println(s);
        }
        System.out.println("third element: "+arrayList.get(3));
        arrayList.remove(2);
        System.out.println("third element after removing of second: " + arrayList.get(3));
    }
}
