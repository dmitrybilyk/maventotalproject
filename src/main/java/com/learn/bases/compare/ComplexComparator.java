package com.learn.bases.compare;

import java.util.Comparator;

/**
 * Created by IntelliJ IDEA.
 * User: bilyk
 * Date: 07.05.12
 * Time: 15:27
 * To change this template use File | Settings | File Templates.
 */
public class ComplexComparator implements Comparator<Human> {

    public int compare(Human o1, Human o2) {
        if(o1.getSurName().compareTo(o2.getSurName()) == 0){
            return o1.getHeight() - o2.getHeight();
        }else{
            if(o1.getSurName().compareTo(o2.getSurName())>0){
                return 1;
            }else{
                return -1;
            }
        }
    }
}
