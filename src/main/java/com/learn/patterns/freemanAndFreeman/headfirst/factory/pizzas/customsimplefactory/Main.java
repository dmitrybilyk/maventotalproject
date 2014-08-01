package com.learn.patterns.freemanAndFreeman.headfirst.factory.pizzas.customsimplefactory;

/**
 * Created by bid on 8/1/14.
 */
public class Main
{
   public static void main(String[] args)
   {
      PhoneProducer phoneProducer = new PhoneProducer(new PhoneProducingFabric());
      phoneProducer.produceAndSellThePhone("cellPhone");
//      phoneProducer.produceAndSellThePhone("portablePhone"); //portable panasonic phone will appear
//      phoneProducer.produceAndSellThePhone("ddfdfs"); //default nokia phone will appear
   }
}
