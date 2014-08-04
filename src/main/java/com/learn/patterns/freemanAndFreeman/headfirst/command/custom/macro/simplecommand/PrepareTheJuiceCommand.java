package com.learn.patterns.freemanAndFreeman.headfirst.command.custom.macro.simplecommand;

/**
 * Created by bid on 8/4/14.
 */
public class PrepareTheJuiceCommand implements Command
{
   private JuiceCreator juiceCreator;

   public PrepareTheJuiceCommand()
   {
      this.juiceCreator = new JuiceCreator();
   }

   @Override
   public void execute()
   {
      juiceCreator.mixTheJuice();
   }
}
