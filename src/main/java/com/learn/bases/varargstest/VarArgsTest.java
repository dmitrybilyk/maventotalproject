package com.learn.bases.varargstest;

/**
 * Created by IntelliJ IDEA.
 * User: bilyk
 * Date: 24.05.12
 * Time: 17:38
 * To change this template use File | Settings | File Templates.
 */
public class VarArgsTest {
    public static void main(String[] args){
         VarArgsTest varArgsTest  = new VarArgsTest();
        varArgsTest.printVarArgs("This is: ", 1,2,3,4,5);
    }
    
    void printVarArgs(String s, int... ints){
        for(int item: ints){
            System.out.println(s + item);
        }
    }
    
    
}
