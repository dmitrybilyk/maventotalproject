package com.learn.patterns.freemanAndFreeman.headfirst.proxy.javaproxy.customjavaproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by bid on 8/13/14.
 */
public class NotAllowVoteTwiceInvocationHandler implements InvocationHandler
{
   private Voter voter;

   public NotAllowVoteTwiceInvocationHandler(
           final Voter voter)
   {
      this.voter = voter;
   }

   @Override
   public Object invoke(final Object proxy, final Method method, final Object[] args) throws IllegalAccessException
   {
      try {
         if (method.getName().startsWith("get")) {
            return method.invoke(voter, args);
         } else if (method.getName().equals("vote")) {
            try {
               throw new IllegalAccessException();
            } catch(IllegalAccessException iae){
               System.out.println("The user " + ((VoterImpl) args[0]).getName() + " has already voted");
            }
         } else if (method.getName().startsWith("set")) {
            return method.invoke(voter, args);
         }
      } catch (InvocationTargetException e) {
         e.printStackTrace();
      }
      return null;
   }
}
