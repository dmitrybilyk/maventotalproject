package com.learn.spring.springinaction.di.model;

import java.util.List;
import java.util.Set;

/**
 * Created by dmitry on 01.05.14.
 */
public class Barsa extends Team {

    @Override
    public void perfom() {
        System.out.println(this.getClass().getName() + " is playing with short passes!");
    }
}
