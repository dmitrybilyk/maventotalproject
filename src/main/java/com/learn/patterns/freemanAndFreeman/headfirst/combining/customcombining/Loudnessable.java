package com.learn.patterns.freemanAndFreeman.headfirst.combining.customcombining;

/**
 * Created by bid on 8/14/14.
 */
public class Loudnessable implements Soundable, Observable
{
   Soundable soundable;

   private int loudness = 5;

   public Loudnessable(final Soundable soundable)
   {
      this.soundable = soundable;
   }

   @Override
   public void playMusic()
   {
      System.out.println("The loudness is equal to " + loudness);
      soundable.playMusic();
   }

   @Override
   public void registerObserver(final Observer observer)
   {
      observers.add(observer);
   }

   public int getLoudness()
   {
      return loudness;
   }

   public void setLoudness(final int loudness)
   {
      this.loudness = loudness;
      notifyObservers();
   }

   @Override
   public void notifyObservers()
   {
      for (Observer observer : observers)
      {
         observer.update(this);
      }
   }
}
