package com.learn.patterns.freemanAndFreeman.headfirst.adapter.custom;

/**
 * Created by bid on 8/4/14.
 */
public class ProfessionalFootballPlayer implements FootballPlayer
{
   @Override
   public void run()
   {
      System.out.println(this.getClass().getCanonicalName() + " is running");
   }

   @Override
   public void kickTheBall()
   {
      System.out.println(this.getClass().getCanonicalName() + " is kicking the ball");
   }

   @Override
   public void train()
   {
      System.out.println(this.getClass().getCanonicalName() + " is training");
   }
}
