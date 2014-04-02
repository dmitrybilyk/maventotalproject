package com.learn.groovy

import groovy.swing.SwingBuilder

/**
 * Created with IntelliJ IDEA.
 * User: dmitriy.bilyk
 * Date: 18.03.13
 * Time: 22:09
 * To change this template use File | Settings | File Templates.
 */
class SwingGroovyLearn {
    // lets create a form with a label & text field for each property of a bean
    public static void main(String[] args) {
        def swing = new SwingBuilder()
        def widget = swing.frame(title:'My Frame', defaultCloseOperation:javax.swing.WindowConstants.EXIT_ON_CLOSE) {
            panel() {
                for (entry in someBean) {
                    label(text:entry.key)
                    textField(text:entry.value)
                }
                button(text:'OK', actionPerformed:{ println("I've been clicked with event ${it}") })
            }
        }.show()
    }

}
