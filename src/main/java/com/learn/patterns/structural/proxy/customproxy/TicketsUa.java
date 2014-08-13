package com.learn.patterns.structural.proxy.customproxy;

/**
 * Created by bid on 8/13/14.
 */
public interface TicketsUa
{
   public boolean checkTickets();

   public void buyTicket() throws UserNotAuthorizedException;
}
