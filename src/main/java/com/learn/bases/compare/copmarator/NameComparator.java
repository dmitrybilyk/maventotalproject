package com.learn.bases.compare.copmarator;

import java.util.Comparator;

/**
 * Created by IntelliJ IDEA.
 * User: bilyk
 * Date: 07.05.12
 * Time: 10:50
 * To change this template use File | Settings | File Templates.
 */
public class NameComparator implements Comparator {
    public int compare(Object o1, Object o2) {
        String humanName1 = ((Human)o1).getSurName();
        String humanName2 = ((Human)o2).getSurName();

        return humanName1.compareTo(humanName2);
    }
}
