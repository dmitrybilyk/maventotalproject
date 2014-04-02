package com.learn.patterns.behavioral.state.withpattern;

/**
 * Created with IntelliJ IDEA.
 * User: dmitriy.bilyk
 * Date: 07.11.13
 * Time: 21:00
 * To change this template use File | Settings | File Templates.
 */
//Behaviour depends on the internal state of the object itself.
//The benefits of using State pattern to implement polymorphic behavior is
// clearly visible, the chances of error are less and it’s very easy to
// add more states for additional behavior making it more robust, easily
// maintainable and flexible. Also State pattern helped in avoiding if-else
// or switch-case conditional logic in this scenario.
public class TVContext implements State {

    private State tvState;

    public void setState(State state) {
        this.tvState=state;
    }

    public State getState() {
        return this.tvState;
    }

    @Override
    public void doAction() {
        tvState.doAction();
    }

}