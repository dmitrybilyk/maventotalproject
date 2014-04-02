package com.learn.bases.compare.copmarator;

import java.util.Comparator;

/**
 * Created by IntelliJ IDEA.
 * User: bilyk
 * Date: 07.05.12
 * Time: 10:41
 * To change this template use File | Settings | File Templates.
 */
public class HeightComparator implements Comparator{
    public int compare(Object o1, Object o2) {
        int height1 = ((Human)o1).getHeight();
        int height2 = ((Human)o2).getHeight();
        return height1-height2;
    }
}
