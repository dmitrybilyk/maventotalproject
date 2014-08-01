package com.learn.patterns.freemanAndFreeman.headfirst.factory.pizzafm.customfactorymethodfactory;

/**
 * Created by bid on 8/1/14.
 */
public class CellPhone extends Phone
{
   public CellPhone()
   {
      name = "cell phone";
      model = "Samsung";
      weight = 200;

      details.add("cells");
      details.add("screen");
   }

   @Override
   public void enhancePhone()
   {
      System.out.println("Enhancing the cell phone");
   }
}
