package com.learn.patterns.freemanAndFreeman.headfirst.command.custom.undo;

/**
 * Created by bid on 8/4/14.
 */
public class Main
{
   public static void main(String[] args)
   {
      Restaurant restaurant = new Restaurant();
      restaurant.setCommand(new PrepareTheJuiceCommand());
      restaurant.setCommand(new PrepareTheSoupCommand());
      restaurant.makeAnOrder();
   }
}
