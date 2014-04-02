package com.learn.bases.genericstest.tutorial1;

/**
 * Created with IntelliJ IDEA.
 * User: dmitriy.bilyk
 * Date: 10.04.13
 * Time: 21:38
 * To change this template use File | Settings | File Templates.
 */
public class TwoObjectsGeneric<T, V> {
    T ob1;

    V ob2;

    TwoObjectsGeneric(T o1, V o2) {
        ob1 = o1;
        ob2 = o2;
    }

    void showTypes() {
        System.out.println("Type of T is " + ob1.getClass().getName());

        System.out.println("Type of V is " + ob2.getClass().getName());
    }

    T getob1() {
        return ob1;
    }

    V getob2() {
        return ob2;
    }


    public static void main(String args[]) {
        TwoObjectsGeneric<Integer, String> tgObj = new TwoObjectsGeneric<Integer, String>(88, "Generics");
        tgObj.showTypes();

        int v = tgObj.getob1();
        System.out.println("value: " + v);

        String str = tgObj.getob2();
        System.out.println("value: " + str);
}
}
