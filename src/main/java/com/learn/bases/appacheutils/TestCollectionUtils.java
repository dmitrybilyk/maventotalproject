package com.learn.bases.appacheutils;

import org.apache.commons.collections.CollectionUtils;

import java.util.*;
import org.apache.commons.collections.*;

import java.util.Arrays;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Nata
 * Date: 4/7/13
 * Time: 7:33 AM
 * To change this template use File | Settings | File Templates.
 */
public class TestCollectionUtils {

    public static void main(String[] args)
    {
//
//        //Closure
        System.out.println("\nTest Number One Results :");
        List<String> collectionOfWords = Arrays.asList("Java", "Example",
                "Help", "Tips", "And",
                "Tricks", "Apache",
                "Commons", "Collections");
        // Lets call toString on every object and print it out.
        CollectionUtils.forAllDo(collectionOfWords, new Closure() {
            public void execute(Object o) {
                System.out.print(o.toString() + " ");
            }
        });

        //Transformer

        Collection<String> stringOfNumbers = Arrays.asList("1", "2", "3", "4");
        Collection<Integer> intNums = CollectionUtils.collect(stringOfNumbers, new Transformer() {
            public Object transform(Object o) {
                return Integer.valueOf((String) o);
            }
        });
        add10Closure(intNums);
        printClosure(intNums);

    }


    private static void printClosure(Collection<Integer> o){
        CollectionUtils.forAllDo(o, new Closure() {
            public void execute(Object o) {
                System.out.print(o.toString() + " ");
            }
        });
    }

    private static void add10Closure(Collection<Integer> o){
        CollectionUtils.forAllDo(o, new Closure() {
            public void execute(Object o) {
                o = (Integer)o + 10;
            }
        });
    }
}
