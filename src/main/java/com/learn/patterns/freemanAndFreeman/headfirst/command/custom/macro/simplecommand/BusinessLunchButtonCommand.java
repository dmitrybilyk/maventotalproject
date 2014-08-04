package com.learn.patterns.freemanAndFreeman.headfirst.command.custom.macro.simplecommand;

import java.util.List;

/**
 * Created by bid on 8/4/14.
 */
public class BusinessLunchButtonCommand implements Command
{
   private List<Command> listOfCommands;

   public BusinessLunchButtonCommand(
           final List<Command> listOfCommands)
   {
      this.listOfCommands = listOfCommands;
   }

   @Override
   public void execute()
   {
      System.out.println("Preparing the business lunch!!!");
      for (Command command : listOfCommands)
      {
         command.execute();
      }
   }
}
