package com.learn.patterns.freemanAndFreeman.headfirst.combining.customcombining;

/**
 * Created by bid on 8/14/14.
 */
public class Main
{
   public static void main(String[] args)
   {
      AbstractQuaqableFabric fabric = new SoundableLoudnableFabric();

      Loudnessable radio = fabric.createRadioPlayer();

      Observer observer = new NeibourObserver();
      radio.registerObserver(observer);

      radio.setLoudness(7);

      Soundable pocketMP3Player = fabric.createPocketPlayer();
      Soundable tvSetPlayer = fabric.createTVPlayer();

      playMusic(radio);
      playMusic(pocketMP3Player);
      playMusic(tvSetPlayer);
   }

   private static void playMusic(Soundable soundable){
      soundable.playMusic();
   }

}
