package com.learn.patterns.freemanAndFreeman.headfirst.factory.pizzafm.customfactorymethodfactory;

/**
 * Created by bid on 8/1/14.
 */
public class TouchScreenPhone extends Phone
{
   @Override
   public void selling()
   {
      System.out.println("We are touchpads via ordinary post");
   }

   public TouchScreenPhone()
   {
      name = "without keyboard phone";
      model = "HTC";
      weight = 177;

      details.add("touch screen");
      details.add("main touchphone detail");

   }

   @Override
   public void enhancePhone()
   {
      System.out.println("Enhancing the touch screen phone");
   }
}
