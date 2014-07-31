package com.learn.patterns.freemanAndFreeman.headfirst.decorator.custom;

import java.math.BigDecimal;

/**
 * Created by bid on 7/31/14.
 */
public class EconomRoom extends Room
{

   public EconomRoom()
   {
      name = "Econom Room";
   }

   @Override
   public BigDecimal cost()
   {
      return new BigDecimal(100.0);
   }
}
