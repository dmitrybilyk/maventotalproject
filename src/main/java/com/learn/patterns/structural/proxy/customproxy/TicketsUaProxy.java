package com.learn.patterns.structural.proxy.customproxy;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by bid on 8/13/14.
 */
public class TicketsUaProxy implements TicketsUa
{
   TicketsUa ticketsUa;
   UserDB userDb = new UserDB();
   private String userName;

   public TicketsUaProxy(String userName)
   {
      this.ticketsUa = new TicketsUaImpl();
      this.userName = userName;
   }

   @Override
   public boolean checkTickets()
   {
         return true;
   }

   @Override
   public void buyTicket() throws UserNotAuthorizedException
   {
      if (userDb.getUsers().containsKey(userName))
      {
         System.out.println("Ticket is bought successfully");
      } else {
         throw new UserNotAuthorizedException();
      }
   }

}
