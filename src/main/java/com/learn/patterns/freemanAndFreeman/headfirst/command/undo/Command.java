package com.learn.patterns.freemanAndFreeman.headfirst.command.undo;

public interface Command {
	public void execute();
	public void undo();
}
