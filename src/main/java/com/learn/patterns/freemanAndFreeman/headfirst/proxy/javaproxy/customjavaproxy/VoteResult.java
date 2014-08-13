package com.learn.patterns.freemanAndFreeman.headfirst.proxy.javaproxy.customjavaproxy;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by bid on 8/13/14.
 */
public class VoteResult
{
   private Map<Voter, YesOrNoEnum> resultsMap;

   private int                yesCount   = 0;
   private int                noCount    = 0;


   public VoteResult()
   {
      this.resultsMap = new HashMap<Voter, YesOrNoEnum>();
   }

   public int getYesCount()
   {
      return yesCount;
   }

   public void setYesCount(final int yesCount)
   {
      this.yesCount = yesCount;
   }

   public int getNoCount()
   {
      return noCount;
   }

   public void setNoCount(final int noCount)
   {
      this.noCount = noCount;
   }

   public void voteYes(final Voter voter){
      resultsMap.put(voter, YesOrNoEnum.YES);
      setYesCount(yesCount += 1);
   }

   public void voteNo(final Voter voter){
      resultsMap.put(voter, YesOrNoEnum.NO);
      setNoCount(noCount += 1);
   }

   public Map<Voter, YesOrNoEnum> getResultsMap()
   {
      return resultsMap;
   }

   public void setResultsMap(final Map<Voter, YesOrNoEnum> resultsMap)
   {
      this.resultsMap = resultsMap;
   }

   public void printResults()
   {
      for (Voter voter : resultsMap.keySet())
      {
         System.out.println(voter.getName() + " voted for " + resultsMap.get(voter).getStringValue());
      }
      System.out.println("__________________________________________________");
      System.out.println("Total yes - " + yesCount);
      System.out.println("Total no - " + noCount);
   }
}
