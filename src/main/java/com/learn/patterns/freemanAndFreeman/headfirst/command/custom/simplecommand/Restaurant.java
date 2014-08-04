package com.learn.patterns.freemanAndFreeman.headfirst.command.custom.simplecommand;

/**
 * Created by bid on 8/4/14.
 */
public class Restaurant
{
   private Command command;

   public void setCommand(final Command command)
   {
      this.command = command;
   }

   public void makeAnOrder(){
      command.execute();
   }
}
