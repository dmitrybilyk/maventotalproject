package com.learn.patterns.freemanAndFreeman.headfirst.combining.customcombining;

import com.learn.patterns.freemanAndFreeman.headfirst.facade.custom.Loudness;

/**
 * Created by bid on 8/14/14.
 */
public class NeibourObserver implements Observer
{
   @Override
   public void update(final Loudnessable loudnessable)
   {
      System.out.println("The sound of " + loudnessable.getClass().getCanonicalName() + " is " + loudnessable);
   }
}
