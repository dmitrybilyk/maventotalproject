package com.learn.patterns.freemanAndFreeman.headfirst.facade.custom;

/**
 * Created by bid on 8/5/14.
 */
public class WatchingTheFootballOnTV
{

   private Loudness loudness = Loudness.LOW;

   public void turningOnTheTV(){
      System.out.println("Turning on the TV set");
   }

   public void turningOffTheTV(){
      System.out.println("Turning off the TV set");
   }

   public Loudness getLoudness()
   {
      return loudness;
   }

   public void setLoudness(final Loudness loudness)
   {
      this.loudness = loudness;
   }
}
