package com.learn.patterns.freemanAndFreeman.headfirst.factory.pizzafm.customfactorymethodfactory;

/**
 * Created by bid on 8/1/14.
 */
public class ChinaCellPhone extends Phone
{
   public ChinaCellPhone()
   {
      name = "china cell phone";
      model = "Samsung - china";
      weight = 100;

      details.add("china cells");
      details.add("china screen");
   }

   @Override
   public void enhancePhone()
   {
      System.out.println("Enhancing the china cell phone");
   }

   @Override
   public void selling()
   {
      System.out.println("We are selling cell phones via speed internet very quickly");
   }

}
