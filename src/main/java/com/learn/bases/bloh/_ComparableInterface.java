package com.learn.bases.bloh;

import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created with IntelliJ IDEA.
 * User: flash
 * Date: 12.12.12
 * Time: 8:58
 * To change this template use File | Settings | File Templates.
 */
public class _ComparableInterface {
    //When you implement Comparable, your class maintains ordered comparing.(natural ordering)
    //Sorting of array object that contains elements that are comparable is simple:
    //Arrays.sort(a);

    //Conventions are the same as for equals(). The difference between them - is only that compareTo()
    //makes ordering comparing.

}
// The next class sorts objects of array (be cours String implements Comparable) and removes
//duplicates:
class WordList{
    private static String[] args2 = {"f", "d", "f"};
    public static void main(String[] args) {
        args = args2;
        Set s = new TreeSet();
        s.addAll(Arrays.asList(args));
        System.out.println(s);
    }
}
