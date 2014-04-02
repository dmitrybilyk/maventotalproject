package com.learn.bases.innerclasses.anotherpackage;

/**
 * Created by IntelliJ IDEA.
 * User: Flash
 * Date: 05.05.12
 * Time: 16:13
 * To change this template use File | Settings | File Templates.
 */
public class InsidePackageClass extends AnotherClass{
    public void print(){
        System.out.println(protectedInt);
    }
    public static void main(String[] args){
        InsidePackageClass insidePackageClass = new InsidePackageClass();
        insidePackageClass.print();
        insidePackageClass.setDefaultInt(50);
        insidePackageClass.setProtectedInt(150);
        insidePackageClass.print();
    }
}
