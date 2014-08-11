package com.learn.patterns.freemanAndFreeman.headfirst.iterator.custom;

/**
 * Created by bid on 8/11/14.
 */
public class BugTask extends Task
{

   @Override
   public MyIterator createIterator()
   {
      return new BugTaskIterator();
   }
}
