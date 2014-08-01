package com.learn.patterns.freemanAndFreeman.headfirst.factory.pizzaaf.customabstractfactoryfactory;

/**
 * Created by bid on 8/1/14.
 */
public class DefaultNokiaPhone extends Phone
{
   public DefaultNokiaPhone()
   {
      {
         name = "cell phone";
         model = "Nokia 1100";
         weight = 150;

         details.add("nokia good details");
         details.add("nokia good screen");
      }
   }

   @Override
   public void enhancePhone()
   {
      System.out.println("Enhancing the nokia default phone");
   }
}
