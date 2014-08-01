package com.learn.patterns.freemanAndFreeman.headfirst.factory.pizzas.customsimplefactory;

/**
 * Created by bid on 8/1/14.
 */
public abstract class Phone
{
   protected String name;
   protected String model;
   protected int weight;

   public void buyAdditionalDetails()
   {
      System.out.println("Buying additional details for phone: " + this.getName());
   }
   public void enhancePhone()
   {
      System.out.println("Enhancing the phone: " + this.getName()+ ", " + this.getModel() + ", " + this.getWeight());
   }

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
}
