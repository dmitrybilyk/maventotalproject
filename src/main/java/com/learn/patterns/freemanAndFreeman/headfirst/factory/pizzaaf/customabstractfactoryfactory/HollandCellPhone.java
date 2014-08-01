package com.learn.patterns.freemanAndFreeman.headfirst.factory.pizzaaf.customabstractfactoryfactory;

/**
 * Created by bid on 8/1/14.
 */
public class HollandCellPhone extends Phone
{
   public HollandCellPhone(KeyboardFactory keyboardFactory)
   {
      name = "holland cell phone";
      model = "Samsung - Holland";
      weight = 350;
      details.add(keyboardFactory.createKeyboard());
      details.add("holland cells");
      details.add("holland screen");
   }

   @Override
   public void enhancePhone()
   {
      System.out.println("Enhancing the Holland cell phone");
   }
}
