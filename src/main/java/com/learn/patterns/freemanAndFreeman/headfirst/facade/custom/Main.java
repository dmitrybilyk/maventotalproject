package com.learn.patterns.freemanAndFreeman.headfirst.facade.custom;

/**
 * Created by bid on 8/5/14.
 */
public class Main
{
   public static void main(String[] args) throws InterruptedException
   {
      MyEveningFacade myEveningFacade = new MyEveningFacade(new PreparingTheSofa(), new TalkingToLorraine(),
                                                            new ListeningToTheMusic(), new WatchingTheFootballOnTV());

      myEveningFacade.startTalkingToLorraine();
      Thread.sleep(3000);
      myEveningFacade.stopTalkingToLorraine();
   }
}
