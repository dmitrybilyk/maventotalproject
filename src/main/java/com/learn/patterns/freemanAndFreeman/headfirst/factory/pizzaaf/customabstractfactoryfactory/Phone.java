package com.learn.patterns.freemanAndFreeman.headfirst.factory.pizzaaf.customabstractfactoryfactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bid on 8/1/14.
 */
public abstract class Phone
{
   protected String name;
   protected String model;
   protected int    weight;

   protected Keyboard keyboard;

   private KeyboardFactory keyboardFactory;

   protected List<Object> details = new ArrayList<Object>();


   public Phone(
           final KeyboardFactory keyboardFactory)
   {
      this.keyboardFactory = keyboardFactory;
   }

   public Phone()
   {
   }

   public void buyAdditionalDetails()
   {
      System.out.println("Buying additional details for phone: " + this.getName());
   }

   public abstract void enhancePhone();

   public String getName()
   {
      return name;
   }

   public void setName(final String name)
   {
      this.name = name;
   }

   public String getModel()
   {
      return model;
   }

   public void setModel(final String model)
   {
      this.model = model;
   }

   public int getWeight()
   {
      return weight;
   }

   public void setWeight(final int weight)
   {
      this.weight = weight;
   }

   public void selling(){
      System.out.println("selling through the Internet");
   }

   @Override
   public String toString()
   {
      return "Phone{" +
              "name='" + name + '\'' +
              ", model='" + model + '\'' +
              ", weight=" + weight +
              ", details=" + details +
              '}';
   }

}
