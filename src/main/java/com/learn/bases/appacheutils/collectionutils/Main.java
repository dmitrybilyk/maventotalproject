package com.learn.bases.appacheutils.collectionutils;

import org.apache.commons.collections.Closure;

import java.util.Arrays;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: dmitriy.bilyk
 * Date: 09.04.13
 * Time: 23:08
 * To change this template use File | Settings | File Templates.
 */
public class Main {
    public static void main(String[] args) {
        List list1 = Arrays.asList("Dima", "Vova", "Sasha");
        MyList.each(list1, new Closure() {
            @Override
            public void execute(Object input) {
                System.out.println(input);
            }
        });
    }
}
