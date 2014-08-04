package com.learn.patterns.freemanAndFreeman.headfirst.adapter.custom;

/**
 * Created by bid on 8/4/14.
 */
public class Main
{
   public static void main(String[] args)
   {
       FootballPlayer footballPlayer = new ProfessionalFootballPlayer();
       HockeyPlayer hockeyPlayerWithFootballSkills = new FootballPlayerAdapter(footballPlayer);
       hockeyPlayerWithFootballSkills.kickTheDisc();
   }
}
