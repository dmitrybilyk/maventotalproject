package com.learn.patterns.freemanAndFreeman.headfirst.adapter.custom;

/**
 * Created by bid on 8/4/14.
 */
public class ProfessionalHockeyPlayer implements HockeyPlayer
{
   @Override
   public void skatesRun()
   {
      System.out.println(this.getClass().getCanonicalName() + " is skaterunning");
   }

   @Override
   public void kickTheDisc()
   {
      System.out.println(this.getClass().getCanonicalName() + " is kicking the disc");
   }

   @Override
   public void train()
   {
      System.out.println(this.getClass().getCanonicalName() + " is training");
   }
}
