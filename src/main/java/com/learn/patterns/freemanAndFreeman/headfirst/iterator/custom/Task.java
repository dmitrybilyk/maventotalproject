package com.learn.patterns.freemanAndFreeman.headfirst.iterator.custom;

/**
 * Created by bid on 8/11/14.
 */
public abstract class Task
{
    private String description;
    private int numberOfDevelopers;

   public abstract MyIterator createIterator();


   public String getDescription()
   {
      return description;
   }

   public void setDescription(final String description)
   {
      this.description = description;
   }

   public int getNumberOfDevelopers()
   {
      return numberOfDevelopers;
   }

   public void setNumberOfDevelopers(final int numberOfDevelopers)
   {
      this.numberOfDevelopers = numberOfDevelopers;
   }
}
