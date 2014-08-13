package com.learn.patterns.freemanAndFreeman.headfirst.proxy.javaproxy.customjavaproxy;

/**
 * Created by bid on 8/13/14.
 */
public class PresidentElectionVoter implements Voter
{
   private String name;

   private VoteResult voteResult;

   public PresidentElectionVoter(final String name)
   {
      this.name = name;
      voteResult = new VoteResult();
   }

   @Override
   public String getName()
   {
      return name;
   }

   @Override
   public void setName(final String name)
   {
      this.name = name;
   }

   @Override
   public void vote(Voter voter, final YesOrNoEnum yesOrNo, VoteResult voteResult)
   {
      if (yesOrNo == YesOrNoEnum.YES) {
         voteResult.voteYes(voter);
      } else {
         voteResult.voteNo(voter);
      }
   }
}
