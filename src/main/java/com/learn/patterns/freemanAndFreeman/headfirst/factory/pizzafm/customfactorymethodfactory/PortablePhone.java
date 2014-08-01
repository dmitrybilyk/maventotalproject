package com.learn.patterns.freemanAndFreeman.headfirst.factory.pizzafm.customfactorymethodfactory;

/**
 * Created by bid on 8/1/14.
 */
public class PortablePhone extends Phone
{
   public PortablePhone()
   {
      name = "portable phone";
      model = "Portable Panasonic Phone";
      weight = 900;
   }

   @Override
   public void enhancePhone()
   {
      System.out.println("Enhancing the portable panasonic phone");
   }
}
