package com.learn.patterns.structural.proxy.customproxy;

/**
 * Created by bid on 8/13/14.
 */
public class UserNotAuthorizedException extends Exception
{
   @Override
   public String getMessage()
   {
//      return super.getMessage();
      return "User is not authorized";
   }
}
