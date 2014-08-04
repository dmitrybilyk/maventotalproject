package com.learn.patterns.freemanAndFreeman.headfirst.command.undo;

public class CeilingFanMediumCommand extends CeilingFanCommand {

   public CeilingFanMediumCommand(CeilingFan ceilingFan)
   {
      super.ceilingFan = ceilingFan;
   }

   public void execute() {
		prevSpeed = ceilingFan.getSpeed();
		ceilingFan.medium();
	}

}
