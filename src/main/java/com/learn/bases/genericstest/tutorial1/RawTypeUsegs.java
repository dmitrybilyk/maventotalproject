package com.learn.bases.genericstest.tutorial1;

/**
 * Created with IntelliJ IDEA.
 * User: dmitriy.bilyk
 * Date: 10.04.13
 * Time: 22:04
 * To change this template use File | Settings | File Templates.
 */
public class RawTypeUsegs<T> {
    T ob;

    RawTypeUsegs(T o) {
        ob = o;
    }

    T getob() {
        return ob;
    }

    public static void main(String args[]) {
        RawTypeUsegs<Integer> iOb = new RawTypeUsegs<Integer>(88);
        RawTypeUsegs<String> strOb = new RawTypeUsegs<String>("Generics Test");

        RawTypeUsegs raw = new RawTypeUsegs(new Double(98.6));

        // Cast here is necessary because type is unknown.
        double d = (Double) raw.getob();
        System.out.println("value: " + d);

//        strOb = raw; // OK, but potentially wrong
//        String str = strOb.getob();
//
//        // This assignment also overrides type safety.
//        raw = iOb; // OK, but potentially wrong
//        d = (Double) raw.getob();

}



}