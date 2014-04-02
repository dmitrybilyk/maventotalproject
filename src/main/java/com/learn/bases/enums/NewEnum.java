package com.learn.bases.enums;

/**
 * Created by IntelliJ IDEA.
 * User: bilyk
 * Date: 27.06.12
 * Time: 14:33
 * To change this template use File | Settings | File Templates.
 */
public class NewEnum {
    enum YEAR{
        _2010,
        _2011,
        _2012
    }

    public static void main(String[] args){
        YEAR newEnum = YEAR.valueOf("_2011");
        System.out.println(newEnum.getClass().getName());
        YEAR[] year = YEAR.values();
        for (YEAR year1 : year) {
            System.out.println(year1.name());
        }
        
        switch(newEnum){
            case _2010:
                System.out.println("2010");
                break;
            case _2011:
                System.out.println("2011");
                break;
            case _2012:
                System.out.println("2012");
                break;
        }
        
    }
}
