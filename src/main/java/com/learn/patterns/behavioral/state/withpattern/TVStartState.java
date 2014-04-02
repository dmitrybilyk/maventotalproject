package com.learn.patterns.behavioral.state.withpattern;

/**
 * Created with IntelliJ IDEA.
 * User: dmitriy.bilyk
 * Date: 07.11.13
 * Time: 20:59
 * To change this template use File | Settings | File Templates.
 */
public class TVStartState implements State {

    @Override
    public void doAction() {
        System.out.println("TV is turned ON");
    }

}