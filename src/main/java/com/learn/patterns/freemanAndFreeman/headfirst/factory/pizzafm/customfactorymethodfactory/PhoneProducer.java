package com.learn.patterns.freemanAndFreeman.headfirst.factory.pizzafm.customfactorymethodfactory;

import java.util.List;

/**
 * Created by bid on 8/1/14.
 */
public abstract class PhoneProducer
{
   private String country = "China";

   protected PhoneProducer()
   {
   }

   public abstract Phone produceAndSellThePhone(String model);

   public Phone createPhone(final String model)
   {
    Phone phone = produceAndSellThePhone(model);
      phone.buyAdditionalDetails();
      phone.enhancePhone();
      phone.selling();
      return phone;
   }
}
