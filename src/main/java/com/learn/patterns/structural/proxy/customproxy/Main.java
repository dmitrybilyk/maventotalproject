package com.learn.patterns.structural.proxy.customproxy;

/**
 * Created by bid on 8/13/14.
 */
public class Main
{
   public static void main(String[] args)
   {

      TicketsUa ticketsUaProxy = new TicketsUaProxy("dikd81");
      try
      {
         ticketsUaProxy.buyTicket();
      }
      catch (UserNotAuthorizedException e)
      {
         e.printStackTrace();
      }
   }
}
