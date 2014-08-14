package com.learn.patterns.freemanAndFreeman.headfirst.combining.customcombining;

/**
 * Created by bid on 8/14/14.
 */
public class SoundableLoudnableFabric implements AbstractQuaqableFabric
{
   @Override
   public Loudnessable createRadioPlayer()
   {
      return new Loudnessable(new Radio());
   }

   @Override
   public Loudnessable createPocketPlayer()
   {
      return new Loudnessable(new PocketMP3Player());
   }

   @Override
   public Loudnessable createTVPlayer()
   {
      return new Loudnessable(new TVSetSoundableAdapter(new TvSet()));
   }
}
