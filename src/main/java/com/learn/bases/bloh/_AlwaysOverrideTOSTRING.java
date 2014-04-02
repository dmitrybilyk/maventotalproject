package com.learn.bases.bloh;

/**
 * Created with IntelliJ IDEA.
 * User: flash
 * Date: 12.12.12
 * Time: 8:46
 * To change this template use File | Settings | File Templates.
 */
public class _AlwaysOverrideTOSTRING {
//    ToString() of Object returns name of class + @ plus hashcode of object.
    public static void main(String[] args){
         System.out.println(new ToStringTest());
    }
}

class ToStringTest{
    private String name;
    public String toString(){
        return "test";
    }
}
