package com.learn.groovy

import groovy.time.TimeCategory
import java.text.SimpleDateFormat

/**
 * Created with IntelliJ IDEA.
 * User: Buh
 * Date: 23.03.13
 * Time: 23:57
 * To change this template use File | Settings | File Templates.
 */
class DatesMain {

    public static void main(String[] args) {
        println new Date().time
        def date = new Date() + 1
        println date

        use(TimeCategory){
            println new Date() + 1.week - 1.hour
        }

        def input = "21-03-2007"
        def df1 = new SimpleDateFormat("dd-MM-yyyy")
        def dateVar = df1.parse(input)
        def df2 = new SimpleDateFormat("MMM/dd/yyyy")
        println 'Date was ' + df1.format(dateVar)

        println 'myDate '+ calendar(input).getTime()
    }

    def static calendar(text) {
        Calendar cal = GregorianCalendar.getInstance()
        cal.clear()
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yy")
        sdf.setLenient(false)
        cal.setTime(sdf.parse(text))
        return cal
    }

}
