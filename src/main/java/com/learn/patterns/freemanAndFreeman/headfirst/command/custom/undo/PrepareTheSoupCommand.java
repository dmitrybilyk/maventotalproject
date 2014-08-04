package com.learn.patterns.freemanAndFreeman.headfirst.command.custom.undo;

/**
 * Created by bid on 8/4/14.
 */
public class PrepareTheSoupCommand implements Command
{
   private SoupBaker soupBaker;

   public PrepareTheSoupCommand()
   {
      this.soupBaker = new SoupBaker();
   }

   @Override
   public void execute()
   {
      soupBaker.prepareTheSoup();
   }

   @Override
   public void undo()
   {

   }
}
