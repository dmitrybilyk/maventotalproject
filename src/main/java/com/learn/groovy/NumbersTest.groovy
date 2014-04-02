package com.learn.groovy

/**
 * Created with IntelliJ IDEA.
 * User: Buh
 * Date: 23.03.13
 * Time: 23:53
 * To change this template use File | Settings | File Templates.
 */

//There are no primitives in Groovy. It treats all as objects

class NumbersTest {
    public static void main(String[] args) {
        def x = 3
        def y = 4
        assert x + y == 7
        assert x.plus(y) == 7
        assert x instanceof Integer
        assert 0.5 == 1/2 // uses BigDecimal arithmetic as default
        def a = 2 / 3 // 0.6666666666
        def b = a.setScale(3, BigDecimal.ROUND_DOWN)
        assert b.toString() == '0.666'
        assert 4 + 3 == 7 // 4.plus(3)
        assert 4 - 3 == 1 // 4.minus(3)
        assert 4 * 3 == 12 // 4.multiply(12)
        assert 4 % 3 == 1 // 4.mod(3)
        assert 4 ** 3 == 64 // 4.power(3)
        assert 4 / 3 == 1.3333333333 // 4.div(3)
        assert 4.intdiv(3) == 1 // normal integer division
        assert !(4 == 3)    // !(4.equals(3))
        assert 4 != 3 // ! 4.equals(3)
        assert !(4 < 3)     // 4.compareTo(3) < 0
        assert !(4 <= 3)    // 4.compareTo(3) <= 0
        assert 4 > 3 // 4.compareTo(3) > 0
        assert 4 >= 3 // 4.compareTo(3) >= 0
        assert 4 <=> 3 == 1 // 4.compareTo(3)

        println castToCents(2.0)

    }

    def static castToCents(amt)
    {
        def strAmt = amt.toString()
        def len = strAmt.size()
        def result = ''

        //case no decimal value
        if(strAmt.indexOf('.')== -1 ){
            result=strAmt+'00'
        }

        //case decimal point on the end: example 10.
        else if(strAmt.indexOf('.')== len-1 )
        {
            result=strAmt.replace('.','')+'00'
        }

        //case : decimal value and one sign follows : example 10.5
        else if(strAmt.indexOf('.')==len-2)
        {
            result = strAmt.replace('.','')+'0'
        }

        //case : decimal value and one sign follows : example 0.59
        else if(strAmt.indexOf('.')==len-3)
        {
            result = strAmt.replace('.','')
        }
        return result
    }
}
