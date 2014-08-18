package com.learn.bases.threads.callable.kumar.customcallable;

/**
 * Created by bid on 8/18/14.
 */
public class Student
{
   private String name;
   private int age;

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

   int countAgeMultiple10(){
      return this.age * 10;
   }

   @Override
   public String toString()
   {
      return this.getName()+ ", age - " + this.getAge();
   }
}
