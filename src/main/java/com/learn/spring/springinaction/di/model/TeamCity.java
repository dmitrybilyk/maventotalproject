package com.learn.spring.springinaction.di.model;

/**
 * Created by dmitry on 03.05.14.
 */
public class TeamCity {
    private String name;
    private String state;
    private long population;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public long getPopulation() {
        return population;
    }

    public void setPopulation(long population) {
        this.population = population;
    }
}
