package com.learn.patterns.freemanAndFreeman.headfirst.decorator.custom;

import java.math.BigDecimal;

/**
 * Created by dmitry on 7/23/14.
 */
public abstract class Room {
   protected String name;

   public abstract BigDecimal cost();

   public String getName()
   {
      return name;
   }

   public void setName(String name)
   {
      this.name = name;
   }
}
