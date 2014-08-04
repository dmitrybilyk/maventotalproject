package com.learn.patterns.freemanAndFreeman.headfirst.command.undo;

public class CeilingFanLowCommand extends CeilingFanCommand {

	public void execute() {
		prevSpeed = ceilingFan.getSpeed();
		ceilingFan.low();
	}

}
