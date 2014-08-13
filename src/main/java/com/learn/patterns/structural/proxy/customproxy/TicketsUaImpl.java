package com.learn.patterns.structural.proxy.customproxy;

/**
 * Created by bid on 8/13/14.
 */
public class TicketsUaImpl implements TicketsUa
{
   @Override
   public boolean checkTickets()
   {
      System.out.println("There are tickets in the system you may buy");
      return true;
   }

   @Override
   public void buyTicket()
   {
      System.out.println("You've baught the ticket successfully");
   }
}
