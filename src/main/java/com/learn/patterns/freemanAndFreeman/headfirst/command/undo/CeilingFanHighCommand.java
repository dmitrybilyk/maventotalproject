package com.learn.patterns.freemanAndFreeman.headfirst.command.undo;

public class CeilingFanHighCommand extends CeilingFanCommand {

   public CeilingFanHighCommand(CeilingFan ceilingFan)
   {
      super.ceilingFan = ceilingFan;
   }

   public void execute() {
		prevSpeed = ceilingFan.getSpeed();
		ceilingFan.high();
	}

}
