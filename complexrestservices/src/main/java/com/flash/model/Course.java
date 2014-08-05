package com.flash.model;

/**
 * Created by bid on 8/5/14.
 */
public class Course
{
   private String name;
   private int durability;

   public Course(final String name, final int durability)
   {
      this.name = name;
      this.durability = durability;
   }

   public String getName()
   {
      return name;
   }

   public void setName(final String name)
   {
      this.name = name;
   }

   public int getDurability()
   {
      return durability;
   }

   public void setDurability(final int durability)
   {
      this.durability = durability;
   }
}
