package com.learn.bases.collections;

import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: bilyk
 * Date: 25.06.12
 * Time: 15:52
 * To change this template use File | Settings | File Templates.
 */
public class TestCollections {
    public static void main(String[] args){
        Map<String, String> testMap = new LinkedHashMap<String, String>();
        testMap.put("first", "first");
        testMap.put("second", "second");
        testMap.put("third", "third");
        testMap.put("fourth", "fourth");

        Set<String> enSet = testMap.keySet();
        for (String s : enSet) {
//            System.out.println(testMap.get(s));
        }

        Set<String> testSet = new LinkedHashSet<String>();
        testSet.addAll(enSet);
        for (String s : testSet) {
//            System.out.println(s);
        }

        List<String> list = new LinkedList<String>();
        list.add("fsdf");


        try{
            list.add(1, "dsfgfgf");
        }catch(IndexOutOfBoundsException e){
            System.out.println(e.getMessage());
        }

        for (String s : list) {
            System.out.println(s);
        }
    }
}
