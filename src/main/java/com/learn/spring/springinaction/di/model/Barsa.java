package com.learn.spring.springinaction.di.model;

import java.util.List;
import java.util.Set;

/**
 * Created by dmitry on 01.05.14.
 */
public class Barsa extends Team {

    private Couch couch;

    private String path;

    public String getPath() {
        return path;
    }

    private List<TeamCity> teamCities;

    public List<TeamCity> getTeamCities() {
        return teamCities;
    }

    private List<String> states;

    public List<String> getStates() {
        return states;
    }

    public void setStates(List<String> states) {
        this.states = states;
    }

    public void setTeamCities(List<TeamCity> teamCities) {
        this.teamCities = teamCities;
    }

    public void setPath(String path) {
        this.path = path;
    }

    private String couchName;

    private int couchAge;
    private TeamCity teamCity;

    public int getCouchAge() {
        return couchAge;
    }

    public void setCouchAge(int couchAge) {
        this.couchAge = couchAge;
    }

    public String getCouchName() {
        return couchName;
    }

    public void setCouchName(String couchName) {
        this.couchName = couchName;
    }

    public Couch getCouch() {
        return couch;
    }

    public void setCouch(Couch couch) {
        this.couch = couch;
    }

    @Override
    public void perfom() {
        System.out.println(this.getClass().getName() + " is playing with short passes!");
    }


    public TeamCity getTeamCity() {
        return teamCity;
    }

    public void setTeamCity(TeamCity teamCity) {
        this.teamCity = teamCity;
    }

    public void setCity(TeamCity teamCity) {
        this.teamCity = teamCity;
    }
}
