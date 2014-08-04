package com.learn.patterns.freemanAndFreeman.headfirst.command.custom.undo;

/**
 * Created by bid on 8/4/14.
 */
public interface Command
{
   public void execute();

   public void undo();
}
