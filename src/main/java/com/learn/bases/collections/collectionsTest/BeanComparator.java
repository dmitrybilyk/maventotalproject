package com.learn.bases.collections.collectionsTest;

import java.util.Comparator;

/**
 * Created by IntelliJ IDEA.
 * User: Flash
 * Date: 09.05.12
 * Time: 10:54
 * To change this template use File | Settings | File Templates.
 */
public class BeanComparator implements Comparator<Bean> {
    @Override
    public int compare(Bean o1, Bean o2) {
        String bo1 = ((Bean)o1).getB();
        String bo2 = ((Bean)o2).getB();
        int ao1 = ((Bean)o1).getA();
        int ao2 = ((Bean)o2).getA();

        if(ao2>ao1){
            return 1;
        }else if(ao2<ao1){
            return -1;
        }else{
            return 0;
        }
    }
}
