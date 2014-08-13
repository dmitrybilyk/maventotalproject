package com.learn.patterns.freemanAndFreeman.headfirst.proxy.javaproxy.customjavaproxy;

import com.learn.patterns.freemanAndFreeman.headfirst.proxy.javaproxy.NonOwnerInvocationHandler;
import java.lang.reflect.Proxy;

/**
 * Created by bid on 8/13/14.
 */
public class Main
{

   static VoteResult voteResult = new VoteResult();

   public static void main(String[] args)
   {


      Voter dima = new VoterImpl("Dima");
      vote(dima, YesOrNoEnum.YES);

      Voter ruslan = new VoterImpl("Ruslan");
      vote(ruslan, YesOrNoEnum.YES);

      Voter nikita = new VoterImpl("Nikita");
      vote(nikita, YesOrNoEnum.YES);

      Voter oksana = new VoterImpl("Oksana");
      vote(oksana, YesOrNoEnum.NO);

      Voter natasha = new VoterImpl("Natasha");
      vote(natasha, YesOrNoEnum.NO);

      Voter natashaAgain = new VoterImpl("Natasha");
      vote(natashaAgain, YesOrNoEnum.NO);

      voteResult.printResults();

   }


   public static void vote(Voter voter, YesOrNoEnum yesOrNo){
      if (voteResult.getResultsMap().containsKey(voter)) {
         Voter notAllowedTwiceVoterProxy = (Voter)Proxy.newProxyInstance(
                 voter.getClass().getClassLoader(),
                 voter.getClass().getInterfaces(),
                 new NotAllowVoteTwiceInvocationHandler(voter));
            notAllowedTwiceVoterProxy.vote(voter, yesOrNo, voteResult);
      } else {
         voter.vote(voter, yesOrNo, voteResult);
      }
   }


}
