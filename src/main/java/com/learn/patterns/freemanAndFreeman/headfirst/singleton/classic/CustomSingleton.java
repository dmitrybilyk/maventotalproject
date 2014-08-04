package com.learn.patterns.freemanAndFreeman.headfirst.singleton.classic;

/**
 * Created by bid on 8/4/14.
 */
public class CustomSingleton
{
   private static CustomSingleton instance;

   private CustomSingleton()
   {
   }

   public static CustomSingleton getInstance(){
      if(instance == null){
         instance = new CustomSingleton();
      }
      return instance;
   }
}
