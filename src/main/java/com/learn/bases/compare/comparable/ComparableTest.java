package com.learn.bases.compare.comparable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: bilyk
 * Date: 07.05.12
 * Time: 10:00
 * To change this template use File | Settings | File Templates.
 */
public class ComparableTest {
    public static void main(String [] args){

        TestComparable testComparable1 = new TestComparable(188, "rlex");
        TestComparable testComparable2 = new TestComparable(1977, "Aalex");
        TestComparable testComparable3 = new TestComparable(191, "Man");
        TestComparable testComparable4 = new TestComparable(250, "Man");


        List<TestComparable> testComparableList = new ArrayList<TestComparable>();
        testComparableList.add(testComparable1);
        testComparableList.add(testComparable2);
        testComparableList.add(testComparable3);
        testComparableList.add(testComparable4);

        Collections.sort(testComparableList);

        for (TestComparable testComparable : testComparableList) {
            System.out.println(testComparable.getName());
            System.out.println(testComparable.getAge());
        }

        if(testComparable1.compareTo(testComparable2)>0){
            System.out.println("first grater then second");
        }else if(testComparable1.compareTo(testComparable2)<0){
            System.out.println("second grate then first");
        }else{
            System.out.println("They are equal!");
        }

    }
}
