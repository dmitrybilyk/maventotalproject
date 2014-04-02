package com.learn.bases.enums;

/**
 * Created by IntelliJ IDEA.
 * User: bilyk
 * Date: 27.06.12
 * Time: 15:10
 * To change this template use File | Settings | File Templates.
 */
public enum EnumClass {
    JUNIOR("1"),
    MIDDLE("2"),
    SENYOUR("3");

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    private String value;

    EnumClass(String value) {
        this.value = value;
    }

    public static void main(String[] args){
        System.out.println(MIDDLE.ordinal());
    }
}
