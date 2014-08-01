package com.learn.patterns.freemanAndFreeman.headfirst.factory.pizzas.customsimplefactory;

/**
 * Created by bid on 8/1/14.
 */
public class PhoneProducer
{
   private PhoneProducingFabric simplePhoneFabric;

   public PhoneProducer(
           final PhoneProducingFabric simpleFabric)
   {
      this.simplePhoneFabric = simpleFabric;
   }

   public void produceAndSellThePhone(String model){
      Phone phone = simplePhoneFabric.createPhone(model);
      phone.buyAdditionalDetails();
      phone.enhancePhone();
   }
}
