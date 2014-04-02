package com.learn.bases.overrover.overriding;

/**
 * Created with IntelliJ IDEA.
 * User: flash
 * Date: 20.08.12
 * Time: 20:27
 * To change this template use File | Settings | File Templates.
 */

//Abstract methods must be overriden by the firs concrete class;
//Overriding method:
//- must have the same argument list;
//- must have the same return type;
//- must not have more restricted access modifier;
//- may have a less restrictive access modifier;
//- must not throw new or broader checked exceptions;
//- may throw fewer or narrower checked exceptions or any unchecked exception

//    final methods cannot be overridden, only inherited methods may be overriden;
//we may call original version from superclass with super.superMethod();

public class OverridingTest {
    public static void main(String[] args){
          ChildClass childClass = new ChildClass();
        childClass.printForOverride("hi", 10);
    }
}
