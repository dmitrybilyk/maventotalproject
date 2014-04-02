package com.learn.bases.iterator;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: dmitriy.bilyk
 * Date: 15.01.14
 * Time: 21:53
 * To change this template use File | Settings | File Templates.
 */
public class Main {
    // We can return iterator object to the start of every object, class of wich
    // implements List interface. Also we can return ListIterator. In this case
    // we can go backwards and alter objects.
    public static void main(String[] args) {
        List<String> stringList = getList();

        Iterator iterator = stringList.iterator();

        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }

        System.out.println("list iterrtor");
        ListIterator listIterator = stringList.listIterator();
        while(listIterator.hasNext()){
            Object element = listIterator.next();
            listIterator.set(element + "       |");
        }

        while(listIterator.hasPrevious()){
            Object element = listIterator.previous();
            System.out.println(element);
        }

    }

    private static List<String> getList() {
        List<String> list = new ArrayList<String>();
        for(int i = 1;i <= 5;i++){
            list.add(i+UUID.randomUUID().toString().substring(0, 20));
        }

        return list;
    }


}
