package com.learn.groovy

/**
 * Created with IntelliJ IDEA.
 * User: Buh
 * Date: 24.03.13
 * Time: 0:25
 * To change this template use File | Settings | File Templates.
 */
class UsingClosuresMain {
    public static void main(String[] args) {
        def doubleNum = { num -> num * 2}
            println doubleNum(5) // => 6

        def processThenPrint = { num, closure3 ->
            num = closure3(num); println "num is $num"
        }
//
        processThenPrint(3, doubleNum)    // => num is 6
        processThenPrint(10) { it / 2 }   // => num is 5

        new File('d:/write.txt').eachLine {
            println it
        }

        3.times { println 'Hi' }
        [0, 1, 2].each { number ->
            println number
        }
        [0, 1, 2].each { println it}
        def printit = { println it }
        [0, 1, 2].each printit

        def map = ['a': 1, 'b': 2]
        map.each {key, value -> map[key] = value * 2}
        assert map == ['a': 2, 'b': 4]
        def doubler = {key, value -> map[key] = value * 2}
        map.each(doubler)
        assert map == ['a': 4, 'b': 8]


        assert [1, 2, 3].grep{ it < 3 } == [1, 2]
        assert [1, 2, 3].any{ it % 2 == 0 }
        assert [1, 2, 3].every{ it < 4 }
        assert (1..9).collect{it}.join() == '123456789'
        assert (1..4).collect{it * 2}.join() == '2468'
        def add = { x, y -> x + y }
        def mult = { x, y -> x * y }
        assert add(1, 3)  == 4
        assert mult(1, 3) == 3
        def min = { x, y -> [x, y].min() }
        def max = { x, y -> [x, y].max() }
        def triple = mult.curry(3); assert triple(2) == 6
        def atLeastTen = max.curry(10)
        assert atLeastTen(5)  == 10
        assert atLeastTen(15) == 15

    }
}
