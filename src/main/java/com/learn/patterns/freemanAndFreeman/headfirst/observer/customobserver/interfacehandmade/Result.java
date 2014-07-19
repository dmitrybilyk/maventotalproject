package com.learn.patterns.freemanAndFreeman.headfirst.observer.customobserver.interfacehandmade;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * Created by dmitry on 7/19/14.
 */

@Component
public class Result {
    @Value("2")
    private int localGoals;
    @Value("1")
    private int questGoals;

    public Result() {
    }

    public Result(int localGoals, int questGoals) {
        this.localGoals = localGoals;
        this.questGoals = questGoals;
    }

    public int getLocalGoals() {
        return localGoals;
    }

    public void setLocalGoals(int localGoals) {
        this.localGoals = localGoals;
    }

    public int getQuestGoals() {
        return questGoals;
    }

    public void setQuestGoals(int questGoals) {
        this.questGoals = questGoals;
    }
}
