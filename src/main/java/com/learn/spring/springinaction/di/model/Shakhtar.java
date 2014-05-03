package com.learn.spring.springinaction.di.model;

/**
 * Created by dmitry on 01.05.14.
 */
public class Shakhtar extends Team {
    private String alternativeName;

    @Override
    public void perfom() {
        System.out.println(this.getClass().getCanonicalName() + " is playing in brasilian football!!!");
    }

    public void setAlternativeName(String alternativeName) {
        this.alternativeName = alternativeName;
    }
}
