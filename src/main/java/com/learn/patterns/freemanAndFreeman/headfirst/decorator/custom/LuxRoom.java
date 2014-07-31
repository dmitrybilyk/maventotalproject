package com.learn.patterns.freemanAndFreeman.headfirst.decorator.custom;

import java.math.BigDecimal;

/**
 * Created by bid on 7/31/14.
 */
public class LuxRoom extends Room
{

   public LuxRoom()
   {
      name = "Lux Room";
   }

   @Override
   public BigDecimal cost()
   {
      return new BigDecimal(300);
   }
}
