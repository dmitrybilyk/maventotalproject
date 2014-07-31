package com.learn.patterns.freemanAndFreeman.headfirst.decorator.custom;

import java.math.BigDecimal;

/**
 * Created by bid on 7/31/14.
 */
public class ComfortableMassage extends ComfortDecorator
{
   Room room;

   public ComfortableMassage(final Room room)
   {
      this.room = room;
   }

   @Override
   public BigDecimal cost()
   {
      return room.cost().add(new BigDecimal(100.0));
   }

   @Override
   public String getName()
   {
      return room.getName() + ", " + "Massage";
   }
}
