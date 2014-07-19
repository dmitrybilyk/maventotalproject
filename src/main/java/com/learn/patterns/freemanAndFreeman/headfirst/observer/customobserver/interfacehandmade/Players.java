package com.learn.patterns.freemanAndFreeman.headfirst.observer.customobserver.interfacehandmade;

import org.springframework.stereotype.Component;

/**
 * Created by dmitry on 7/19/14.
 */
@Component
public class Players implements Observer {

    public Players() {
    }

    @Override
    public void update(Result result) {
        System.out.println(String.format("Damn... %s:%s", result.getLocalGoals(), result.getQuestGoals()));
    }
}
