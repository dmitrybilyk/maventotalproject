package com.learn.groovy

/**
 * Created with IntelliJ IDEA.
 * User: USER
 * Date: 9/21/12
 * Time: 6:21 PM
 * To change this template use File | Settings | File Templates.
 */
class TestGroovy {
    public static void main(String [] args){
        println 'Hello'
        println 'Hello'
        println 'Hello'
       println 3+3
        def x = true
        println x

        def myList = [4,3,6]
        println myList

        def scores = [ "Brett":100, "Pete":"Did not finish", "Andrew":86.87934 ]
        println(scores.Pete)
        scores["Pete"] = 3
        println(scores["Pete"])


        def amPM = Calendar.getInstance().get(Calendar.AM_PM)
        if (amPM == Calendar.AM)
        {
            println("Good morning")
        } else {
            println("Good evening")
        }

        if (amPM == Calendar.AM)
        {
            println("Have another cup of coffee.")
        } else{
            println("have another glass of milk")
        }


        def i = 20
        def b = 10
        println(b == i)
        println(b.is(i))

        int[] array = [4, 5, 8 ,3]
        for (bb in 0..array.length - 1) {
            println(bb)
        }

        def name='World'; println "Hello $name!"

//        def g = new Greet('world2')  // create object
//        g.salute()

//
//        def (a, bbb) = [1, 2]
//
//        assert a == 4
//        assert bbb == 2


        Date date = Date.parse("dd-MM-yyyy","31-12-2010")
        String out = date.format("MM-dd-yyyy")

        assert out == "12-31-2010"

//        The Calendar class has a getActualMaximum() method that we can use to find the last day of a month.
        Calendar calendar = GregorianCalendar.instance
        calendar.set(2010,3,1) // 1st April 2010

        def lastDay = calendar.getActualMaximum(GregorianCalendar.DAY_OF_WEEK_IN_MONTH)
//        assert lastDay == 30
        println(lastDay)

        def num = 123
        def numPadded = String.format("%05d", num)
        assert numPadded == '00123'

//        If you want to pad using spaces you would use a format string: %5d
//
//        If you want to pad using zeros then add a zero after the percent: %05d


//        Groovy provides a string function isInteger() that tests if a string is an integer:
        def num1 = "12345"
        def num2 = "123.45"
        assert num1.isInteger()
        assert !num2.isInteger()



//        A Groovy list is a Java ArrayList which provides a contains() method.
        def list3 = [1,3,5]
        assert list3.contains(3)
        assert !list3.contains(4)

//        Alternatively you can use Groovy’s in operator.
                def list2 = [1,3,5]
        assert 3 in list2
        assert !(4 in list2)




//        Note that this method may provide unexpected results when comparing Strings and GStrings. For example, the String “1″ and the GString “1″ are not considered to be equal.
                def one = 1
        def list = ["1","2","3"]

//These are false
//        assert list.contains( "${one}" )
//        assert "${one}" in list

// These are true
        assert list.contains( "1" )
        assert "1" in list

// Convert GStrings to Strings so these are also true
        assert list.contains( "${one}".toString() )
        assert "${one}".toString() in list
        def d  = "3"
        System.out.println(pad(d, 2))


    }
//
//    static class Greet {
//        def name
//        Greet(who) { name = who[0].toUpperCase() +
//                who[1..-1] }
//        def salute() { println "Hello $name!" }
//    }
}