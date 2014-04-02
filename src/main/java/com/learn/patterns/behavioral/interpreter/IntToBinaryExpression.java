package com.learn.patterns.behavioral.interpreter;

/**
 * Created with IntelliJ IDEA.
 * User: dmitriy.bilyk
 * Date: 11.11.13
 * Time: 21:40
 * To change this template use File | Settings | File Templates.
 */
public class IntToBinaryExpression implements Expression {

    private int i;

    public IntToBinaryExpression(int c){
        this.i=c;
    }
    @Override
    public String interpret(InterpreterContext ic) {
        return ic.getBinaryFormat(this.i);
    }

}
