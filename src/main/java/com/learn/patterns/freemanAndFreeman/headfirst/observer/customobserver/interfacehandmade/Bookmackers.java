package com.learn.patterns.freemanAndFreeman.headfirst.observer.customobserver.interfacehandmade;

import org.springframework.stereotype.Component;

/**
 * Created by dmitry on 7/19/14.
 */

@Component
public class Bookmackers implements Observer{

    public Bookmackers() {
    }

    @Override
    public void update(Result result) {
        System.out.println(String.format("result %s:%s is a great result for our profit!", result.getLocalGoals(), result.getQuestGoals()));
    }
}
