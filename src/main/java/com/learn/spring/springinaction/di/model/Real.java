package com.learn.spring.springinaction.di.model;

/**
 * Created by dmitry on 01.05.14.
 */
public class Real extends Team{
    @Override
    public void perfom() {
        System.out.println(this.getClass().getName() + " is playing with middle passes!");
    }
}
