package com.learn.patterns.freemanAndFreeman.headfirst.command.undo;

/**
 * Created by bid on 8/4/14.
 */
public class CeilingFanCommand implements Command
{
   CeilingFan ceilingFan;
   int prevSpeed;

   public CeilingFanCommand()
   {
   }

   public CeilingFanCommand(CeilingFan ceilingFan) {
      this.ceilingFan = ceilingFan;
   }

   @Override
   public void execute()
   {

   }

   @Override
   public void undo()
   {
      if (prevSpeed == CeilingFan.HIGH) {
         ceilingFan.high();
      } else if (prevSpeed == CeilingFan.MEDIUM) {
         ceilingFan.medium();
      } else if (prevSpeed == CeilingFan.LOW) {
         ceilingFan.low();
      } else if (prevSpeed == CeilingFan.OFF) {
         ceilingFan.off();
      }
   }
}
