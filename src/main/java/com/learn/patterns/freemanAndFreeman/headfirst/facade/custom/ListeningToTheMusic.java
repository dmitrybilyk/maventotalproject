package com.learn.patterns.freemanAndFreeman.headfirst.facade.custom;

/**
 * Created by bid on 8/5/14.
 */
public class ListeningToTheMusic
{
   private Loudness loudness = Loudness.LOW;

   public void turnOnTheMusic(){
      System.out.println("Turning on the music");
   }

   public void turnOffTheMusic(){
      System.out.println("Turning off the music");
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
