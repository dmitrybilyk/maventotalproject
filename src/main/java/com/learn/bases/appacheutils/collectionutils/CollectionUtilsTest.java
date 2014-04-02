package com.learn.bases.appacheutils.collectionutils;

import org.apache.commons.collections.Closure;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.apache.commons.collections.Transformer;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: dmitriy.bilyk
 * Date: 09.04.13
 * Time: 22:07
 * To change this template use File | Settings | File Templates.
 */
public class CollectionUtilsTest {

    //each
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3, 4);
        CollectionUtils.forAllDo(list, new Closure() {
            public void execute(Object i) {
                System.out.println(i);
            }
        });

        //select
        List<Integer> i = Arrays.asList(1,2,3,4);
        System.out.println(CollectionUtils.select(i, new Predicate() {
            public boolean evaluate(Object o) {
//                return (Integer)o % 2 == 0;
                return (Integer)o == 3 || (Integer)o == 4;
            }
        }));

        //reject
        List<Integer> rejectList = new ArrayList<Integer>(10);
        for (int j = 1; j <= 10; j++) {
            rejectList.add(j);
        }
        List<Integer>  resultReject = (List<Integer>) CollectionUtils.select(rejectList, new Predicate() {
            public boolean evaluate(Object o) {
                return (Integer) o < 5;
            }
        });
        for (Integer integer : resultReject) {
            System.out.println(integer);
        }


        //collect
        List<Integer> transformList = Arrays.asList(1,2,3);
        List<Integer> transformResult = (List<Integer>) CollectionUtils.collect(transformList, new Transformer() {
            public Object transform(Object input) {
                return (Integer) input + 10;
            }
        });
        System.out.println(transformResult);

    }



}
