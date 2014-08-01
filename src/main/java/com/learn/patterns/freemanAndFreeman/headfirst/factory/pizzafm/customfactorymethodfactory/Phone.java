package com.learn.patterns.freemanAndFreeman.headfirst.factory.pizzafm.customfactorymethodfactory;

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

   protected List<String> details = new ArrayList<String>();

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
