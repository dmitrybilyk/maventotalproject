package com.learn.bases.bloh;

/**
 * Created with IntelliJ IDEA.
 * User: flash
 * Date: 09.12.12
 * Time: 13:05
 * To change this template use File | Settings | File Templates.
 */

//For class than is Utilite class - it contais only static methods and vars - you should create
//private empty constructor in order it not to be instantiate. There is no sense to create object
//of such class.

public class _ClassUtilite {
    public static final String TESTVAR = "TESTVALUE";
    private _ClassUtilite(){

    }
}

class main{
    public static void main(String[] args) {
        System.out.println(_ClassUtilite.TESTVAR);
    }
//    _ClassUtilite classUtilite = new _ClassUtilite();
        }
