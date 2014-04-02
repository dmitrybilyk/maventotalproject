package com.learn.groovy

/**
 * Created with IntelliJ IDEA.
 * User: Buh
 * Date: 23.03.13
 * Time: 13:34
 * To change this template use File | Settings | File Templates.
 */
class GroovyInAction {
    public static void main(String[] args) {
        def classes = [String, List]
        for (clazz in classes){
            println clazz.'package'.name
        }

        assert(true)
        assert 1 == 1
        def x = 1
        assert x == 1
        def y = 1 ; assert y == 2


        def d = 'dima'
        def b = 'doma'
        println d
    }


}
