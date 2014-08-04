package com.learn.patterns.freemanAndFreeman.headfirst.singleton.classic;

/**
 * Created by bid on 8/4/14.
 */
public class Main
{
   public static void main(String[] args)
   {
      Singleton singleton = Singleton.getInstance();
      System.out.println(singleton);
      Singleton singleton2 = Singleton.getInstance();
      System.out.println(singleton2);
      Singleton singleton3 = Singleton.getInstance();
      System.out.println(singleton3);
      Singleton singleton4 = Singleton.getInstance();
      System.out.println(singleton4);
   }
}
