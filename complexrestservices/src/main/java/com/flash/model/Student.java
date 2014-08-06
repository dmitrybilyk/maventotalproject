package com.flash.model;

/**
 * Created by bid on 8/5/14.
 */
public class Student
{
   private String name;
   private int age;

   public Student(final String name, final int age)
   {
      this.name = name;
      this.age = age;
   }

   public String getName()
   {
      return name;
   }

   public void setName(final String name)
   {
      this.name = name;
   }

   public int getAge()
   {
      return age;
   }

   public void setAge(final int age)
   {
      this.age = age;
   }
}
