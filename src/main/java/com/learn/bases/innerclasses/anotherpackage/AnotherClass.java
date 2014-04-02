package com.learn.bases.innerclasses.anotherpackage;

/**
 * Created by IntelliJ IDEA.
 * User: Flash
 * Date: 05.05.12
 * Time: 16:07
 * To change this template use File | Settings | File Templates.
 */

//comment
public class AnotherClass {
    private int privateInt;
    protected int protectedInt;
    public int publicInt;
    int defaultInt;
//            fadsfds
    public AnotherClass() {
    }

    public AnotherClass(int privateInt, int protectedInt, int publicInt, int defaultInt) {
        this.privateInt = privateInt;
        this.protectedInt = protectedInt;
        this.publicInt = publicInt;
        this.defaultInt = defaultInt;
    }

    public int getPrivateInt() {
        return privateInt;
    }

    public void setPrivateInt(int privateInt) {
        this.privateInt = privateInt;
    }

    public int getProtectedInt() {
        return protectedInt;
    }

    public void setProtectedInt(int protectedInt) {
        this.protectedInt = protectedInt;
    }

    public int getPublicInt() {
        return publicInt;
    }

    public void setPublicInt(int publicInt) {
        this.publicInt = publicInt;
    }

    public int getDefaultInt() {
        return defaultInt;
    }

    public void setDefaultInt(int defaultInt) {
        this.defaultInt = defaultInt;
    }
}
