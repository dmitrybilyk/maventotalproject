package com.learn.patterns.freemanAndFreeman.headfirst.proxy.javaproxy.customjavaproxy;

/**
 * Created by bid on 8/13/14.
 */
public class VoterImpl implements Voter
{
   private String name;

   public VoterImpl(final String name)
   {
      this.name = name;
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
      if (yesOrNo == YesOrNoEnum.YES){
         voteResult.voteYes(voter);
      } else {
         voteResult.voteNo(voter);
      }
   }

   @Override
   public boolean equals(Object obj)
   {
      return ((VoterImpl) obj).getName().equals(this.getName());
   }

   @Override
   public int hashCode()
   {
      return 31 + name.hashCode();
   }
}
