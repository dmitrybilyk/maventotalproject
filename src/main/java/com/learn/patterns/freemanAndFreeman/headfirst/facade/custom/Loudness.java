package com.learn.patterns.freemanAndFreeman.headfirst.facade.custom;

/**
 * Created by bid on 8/5/14.
 */
public enum Loudness
{
   LOW(1), MEDIUM(5), LOUDLY(10);

   Loudness(final int loudnessLevel)
   {
      this.loudnessLevel = loudnessLevel;
   }

   private int loudnessLevel;

   public int getLoudnessLevel()
   {
      return loudnessLevel;
   }

   public void setLoudnessLevel(final int loudnessLevel)
   {
      this.loudnessLevel = loudnessLevel;
   }
}
