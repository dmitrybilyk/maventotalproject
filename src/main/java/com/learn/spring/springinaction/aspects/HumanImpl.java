package com.learn.spring.springinaction.aspects;

import org.springframework.stereotype.Component;

/**
 * Created by dmitry on 05.05.14.
 */
@Component
public class HumanImpl implements Human {
    @Override
    public void act(String arg1) {
        System.out.println("Human is acting");
    }

    @Override
    public void act() {
        System.out.println("Human is acccctinggg");
    }
}
