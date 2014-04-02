package com.learn.bases.genericstest.tutorial3;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: dmitriy.bilyk
 * Date: 10.04.13
 * Time: 22:53
 * To change this template use File | Settings | File Templates.
 */
public class GenericsTest {
    public static void main(String[] args) {
        Set setOfRawType = new HashSet<String>();
        setOfRawType = new HashSet<Integer>();

        Set<Object> setOfAnyType = new HashSet<Object>();
        setOfAnyType.add("abc"); //legal
        setOfAnyType.add(new Float(3.0f)); //legal - <Object> can accept any type

        Set<?> setOfUnknownType = new LinkedHashSet<String>();
        setOfUnknownType = new LinkedHashSet<Integer>();

        Set<String> setOfString = new HashSet<String>(); //valid in Generics
        setOfString = new LinkedHashSet<String>(); // Ok

//        But Inheritance on type parameter is not supported means Set<Object> will not accept Set<String> as per following Generics code.

//        Set<Object> SetOfObject = new HashSet<String>(); //compiler error - incompatible type

        Set<? extends Number> setOfAllSubTypeOfNumber = new HashSet<Integer>(); //legal - Integer extends Number
        setOfAllSubTypeOfNumber = new HashSet<Float>(); //legal - because Float extends Number



//        Set<? super TreeMap> is another example of bounded wildcards, which will store
//        instances of TreeMap or super class of TreeMap. See following Generics example in Java :
//
        Set<? super TreeMap> setOfAllSuperTypeOfTreeMap = new LinkedHashSet<TreeMap>(); //legal because TreeMap is superType of itself

        setOfAllSuperTypeOfTreeMap = new HashSet<SortedMap>(); //legal because SorteMap is super class of TreeMap
        setOfAllSuperTypeOfTreeMap = new LinkedHashSet<Map>(); //legal since Map is super type of TreeMap


//        Generic Term	Meaning
//        Set<E>	Generic Type , E is called formal parameter
//        Set<Integer>	Parametrized type , Integer is actual parameter here
//        <T extends Comparable>	Bounded type parameter
//        <T super Comparable>	Bounded type parameter
//        Set<?>	Unbounded wildcard
//                <? extends T>	Bounded wildcard type
//                <? Super T>	Bounded wildcards
//        Set	Raw type
//                <T extends Comparable<T>>	Recursive type bound


//        T – used to denote type
//        E – used to denote element
//        K – keys
//        V - values
//        N – for numbers






    }
}
