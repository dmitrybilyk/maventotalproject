package com.learn.patterns.freemanAndFreeman.headfirst.adapter.custom;

/**
 * Created by dmitry on 8/4/14.
 */
public class HockeyPlayerAdapter implements FootballPlayer{
    HockeyPlayer hockeyPlayer;

   public HockeyPlayerAdapter(final HockeyPlayer hockeyPlayer)
   {
      this.hockeyPlayer = hockeyPlayer;
   }


   @Override
   public void run()
   {
      System.out.println("Hockey player is running only on skate");
      hockeyPlayer.skatesRun();
   }

   @Override
   public void kickTheBall()
   {
      System.out.println("Hockey player is kicking the ball only with help of his stick");
   }

   @Override
   public void train()
   {
      hockeyPlayer.train();
   }
}
