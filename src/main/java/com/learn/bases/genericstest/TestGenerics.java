package com.learn.bases.genericstest;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.MAX_VALUE;
import static java.lang.System.*;

/**
 * Created by IntelliJ IDEA.
 * User: Flash
 * Date: 27.05.12
 * Time: 13:10
 * To change this template use File | Settings | File Templates.
 */
public class TestGenerics {
    
    <T> void printStuff(T t){
        if(t instanceof String){
            System.out.println(t);
        }
        if(t instanceof Integer){
            System.out.println(((Integer) t).doubleValue());
        }
    }
    
    
    public static void main(String[] args){
        List<Integer> list = new ArrayList<Integer>();
        list.add(30);
        list.add(20);
        list.add(10);
        list.add(50);
        
        out.println(multiplyAll(list));
        out.println(MAX_VALUE);

        TestGenerics testGenerics = new TestGenerics();
        testGenerics.printStuff("fdsfds");
        testGenerics.printStuff(2);

    }

    private static Integer multiplyAll(List<Integer> list) {
        int sum = 0;
        for (Object o : list) {
//
//  if(!(o instanceof String))
            try{
                sum+=(Integer)o;
            }catch(ClassCastException ex){
                sum+=Integer.parseInt(String.valueOf(o));
            }
        }
        return sum;
    }
}
