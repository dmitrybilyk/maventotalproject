package com.flash.model;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by bid on 8/5/14.
 */


public class Student
{
   @FieldIdentity
   private String name;
   @FieldIdentity
   private int age;

   public Student(final String name, final int age)
   {
      this.name = name;
      this.age = age;
   }

   public Student()
   {

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

   @Target(ElementType.FIELD)
   @Retention(RetentionPolicy.RUNTIME)
   private @interface FieldIdentity
   {
      /**
       * Configuration key.
       */
      String key() default "";
   }



}
