package com.learn.spring.springinaction.di.model;

import java.util.List;
import java.util.Map;

/**
 * Created by dmitry on 01.05.14.
 */
public class Player {
    private String name;
    private int age;
    private int number;
    private Map<String, String> skills;
    private List<String> previousTeams;

    public Player(String name, int age, int number) {
        this.name = name;
        this.age = age;
        this.number = number;
    }

    public Player() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setSkills(Map<String, String> skills) {
        this.skills = skills;
    }

    public void setPreviousTeams(List<String> previousTeams) {
        this.previousTeams = previousTeams;
    }
}
