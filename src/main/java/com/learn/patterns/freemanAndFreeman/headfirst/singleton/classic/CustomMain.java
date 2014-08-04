package com.learn.patterns.freemanAndFreeman.headfirst.singleton.classic;

/**
 * Created by bid on 8/4/14.
 */
public class CustomMain
{
   public static void main(String[] args)
   {
      CustomSingleton singleton = CustomSingleton.getInstance();
      System.out.println(singleton);
      CustomSingleton singleton2 = CustomSingleton.getInstance();
      System.out.println(singleton2);
      CustomSingleton singleton3 = CustomSingleton.getInstance();
      System.out.println(singleton3);
      CustomSingleton singleton4 = CustomSingleton.getInstance();
      System.out.println(singleton4);
   }
}
