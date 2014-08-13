package com.learn.patterns.freemanAndFreeman.headfirst.proxy.javaproxy.customjavaproxy;

/**
 * Created by bid on 8/13/14.
 */
public interface Voter
{
   public String getName();
   public void setName(String name);
   public void vote(Voter voter, YesOrNoEnum yesOrNo, VoteResult voteResult);
}
