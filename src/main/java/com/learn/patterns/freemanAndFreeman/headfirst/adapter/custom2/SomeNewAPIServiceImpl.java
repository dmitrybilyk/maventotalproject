package com.learn.patterns.freemanAndFreeman.headfirst.adapter.custom2;

/**
 * Created by bid on 8/5/14.
 */
public class SomeNewAPIServiceImpl implements SomeNewAPIService
{

   @Override
   public void method1()
   {
      System.out.println("Print from method1 in new api");
   }

   @Override
   public void method2()
   {
      System.out.println("Print from method2 in new api");
   }

   @Override
   public void method3()
   {
      System.out.println("Print from method3 in new api");
   }

   @Override
   public void newTotalPrintMethod()
   {
      System.out.println("Print from new method3 in new api");
   }
}
