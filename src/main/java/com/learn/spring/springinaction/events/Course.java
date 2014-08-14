package com.learn.spring.springinaction.events;

/**
 * Created by dmitry on 04.05.14.
 */
public class Course {
   private String name;
   private int pay;

   public String getName()
   {
      return name;
   }

   public void setName(final String name)
   {
      this.name = name;
   }

   public int getPay()
   {
      return pay;
   }

   public void setPay(final int pay)
   {
      this.pay = pay;
   }
}
