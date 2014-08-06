package com.learn.patterns.freemanAndFreeman.headfirst.facade.custom;

/**
 * Created by bid on 8/5/14.
 */
public class MyEveningFacade
{
   PreparingTheSofa preparingTheSofa;
   TalkingToLorraine talkingToLorraine;
   ListeningToTheMusic listeningToTheMusic;
   WatchingTheFootballOnTV watchingTheFootballOnTV;

   public MyEveningFacade(final PreparingTheSofa preparingTheSofa,
                          final TalkingToLorraine talkingToLorraine,
                          final ListeningToTheMusic listeningToTheMusic,
                          final WatchingTheFootballOnTV watchingTheFootballOnTV)
   {
      this.preparingTheSofa = preparingTheSofa;
      this.talkingToLorraine = talkingToLorraine;
      this.listeningToTheMusic = listeningToTheMusic;
      this.watchingTheFootballOnTV = watchingTheFootballOnTV;
   }

   public void startTalkingToLorraine(){
      listeningToTheMusic.turnOnTheMusic();
      listeningToTheMusic.setLoudness(Loudness.LOW); // as by default
      talkingToLorraine.turnOnTheSkype();
      talkingToLorraine.makingTheCallViaSkype();
   }

   public void stopTalkingToLorraine(){
      listeningToTheMusic.turnOffTheMusic();
      talkingToLorraine.stoppingTheCallViaSkype();
      talkingToLorraine.turnOffTheSkype();
   }

   public void startWatchingFootball(){
      watchingTheFootballOnTV.turningOnTheTV();
      watchingTheFootballOnTV.setLoudness(Loudness.MEDIUM);
      preparingTheSofa.preparingTheSofa();
   }

   public void stopWatchingFootball(){
      watchingTheFootballOnTV.turningOffTheTV();
      preparingTheSofa.foldingTheSofa();
   }

}
