package com.learn.patterns.behavioral.interpreter;

/**
 * Created with IntelliJ IDEA.
 * User: dmitriy.bilyk
 * Date: 11.11.13
 * Time: 21:40
 * To change this template use File | Settings | File Templates.
 */
public interface Expression {
    String interpret(InterpreterContext ic);
}
