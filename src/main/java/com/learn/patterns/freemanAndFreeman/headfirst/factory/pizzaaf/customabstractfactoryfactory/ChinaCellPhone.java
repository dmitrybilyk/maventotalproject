package com.learn.patterns.freemanAndFreeman.headfirst.factory.pizzaaf.customabstractfactoryfactory;

/**
 * Created by bid on 8/1/14.
 */
public class ChinaCellPhone extends Phone
{


   public ChinaCellPhone(KeyboardFactory keyboardFactory)
   {
      name = "china cell phone";
      model = "Samsung - china";
      weight = 100;
      keyboard = keyboardFactory.createKeyboard();
      details.add("china cells");
      details.add("china screen");
      details.add(keyboard);
   }

   @Override
   public void enhancePhone()
   {
      System.out.println("Enhancing the china cell phone");
   }

   public void addingKeyboard(KeyboardFactory keyboardFactory){
      keyboard = keyboardFactory.createKeyboard();
   }

   @Override
   public void selling()
   {
      System.out.println("We are selling cell phones via speed internet very quickly");
   }

}
