package com.learn.patterns.freemanAndFreeman.headfirst.command.custom.macro.simplecommand;

import java.util.ArrayList;
import java.util.List;

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

      List<Command> businessLunchCommandList = new ArrayList<Command>();
      businessLunchCommandList.add(new PrepareTheSoupCommand());
      businessLunchCommandList.add(new PrepareTheJuiceCommand());

      restaurant.setCommand(new BusinessLunchButtonCommand(businessLunchCommandList));
      restaurant.makeAnOrder();

   }
}
