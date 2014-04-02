package com.learn.bases.collections.list;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Buh
 * Date: 19.08.12
 * Time: 13:35
 * To change this template use File | Settings | File Templates.
 */

// List is the interface which allows to store objects in a resizable container.

// ArrayList is implemented as a resizable array. If more elements are added to ArrayList than its initial size, its
// size is increased dynamically. The elements in an ArrayList can be accessed directly and efficiently by using the
// get() and get() methods, since ArrayList is implemented based on an array.

// LinkedList is implemented as a double linked list. Its performance on add() and remove() is better than
// the performance of Arraylist. The get() and get() methods have worse performance than the ArrayList, as the
// LinkedList does not provide direct access.
public class LinkedListTest {
    public static void main(String[] args){
        List<String> list = new ArrayList<String>();
        list.add("one");
        list.add("two");
        list.add("three");
        list.add("four");
        list.add("five");
        list.add("six");
        list.add("seven");

        Collections.sort(list);

        for (String s : list) {
            System.out.println(s);
        }
    }
}
