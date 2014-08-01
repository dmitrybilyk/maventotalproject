package com.learn.patterns.freemanAndFreeman.headfirst.factory.pizzaaf.customabstractfactoryfactory;

/**
 * Created by bid on 8/1/14.
 */
public class Main
{
   public static void main(String[] args)
   {
      PhoneProducer phoneProducer = new HollandPhoneProducer();
      Phone phone = phoneProducer.createPhone("cellPhone");
      System.out.println(phone);
//      phoneProducer.produceAndSellThePhone("portablePhone"); //portable panasonic phone will appear
//      phoneProducer.produceAndSellThePhone("ddfdfs"); //default nokia phone will appear
   }
}
