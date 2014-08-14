package com.learn.spring.springinaction.di.model;

import java.util.Set;
import org.springframework.beans.factory.annotation.Required;

/**
 * Created by dmitry on 01.05.14.
 */
public class Team {
    public Set<Player> players;

    public Set<Player> getPlayers() {
        return players;
    }

    @Required
    public void setPlayers(Set<Player> players) {
        this.players = players;
    }
    public String name;

    public void perfom(){
        System.out.println("We are playing!");
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
