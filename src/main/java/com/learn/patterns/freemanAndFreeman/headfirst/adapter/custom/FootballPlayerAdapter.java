package com.learn.patterns.freemanAndFreeman.headfirst.adapter.custom;

/**
 * Created by dmitry on 8/4/14.
 */
public class FootballPlayerAdapter implements HockeyPlayer{
    FootballPlayer footballPlayer;

    public FootballPlayerAdapter(FootballPlayer footballPlayer) {
        this.footballPlayer = footballPlayer;
    }

    public void skatesRun(){
        System.out.println(footballPlayer.getClass().getCanonicalName() + " is running on skate");
    }

    @Override
    public void kickTheDisc() {
        System.out.println(footballPlayer.getClass().getCanonicalName() + " is kicking the disc");
    }

    @Override
    public void train() {
        footballPlayer.train();
    }

}
