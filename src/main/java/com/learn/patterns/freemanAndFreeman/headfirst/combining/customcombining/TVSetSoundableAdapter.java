package com.learn.patterns.freemanAndFreeman.headfirst.combining.customcombining;

/**
 * Created by bid on 8/14/14.
 */
public class TVSetSoundableAdapter implements Soundable
{
   Watchable watchable;

   public TVSetSoundableAdapter(final Watchable watchable)
   {
      this.watchable = watchable;
   }

   @Override
   public void playMusic()
   {
      System.out.println("We've turned on the music channel and listening to the music");
      watchable.watchTvSet();
   }
}
