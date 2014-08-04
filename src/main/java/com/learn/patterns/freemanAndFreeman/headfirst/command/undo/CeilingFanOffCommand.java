package com.learn.patterns.freemanAndFreeman.headfirst.command.undo;

public class CeilingFanOffCommand extends CeilingFanCommand {

   public CeilingFanOffCommand(final CeilingFan ceilingFan)
   {
      super.ceilingFan = ceilingFan;
   }

   public void execute() {
		prevSpeed = ceilingFan.getSpeed();
		ceilingFan.off();
	}
 
}
