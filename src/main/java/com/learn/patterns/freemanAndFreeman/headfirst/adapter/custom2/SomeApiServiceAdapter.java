package com.learn.patterns.freemanAndFreeman.headfirst.adapter.custom2;

/**
 * Created by bid on 8/5/14.
 */
public class SomeApiServiceAdapter implements SomeNewAPIService
{

   private SomeAPIService someAPIService;

   public SomeApiServiceAdapter(final SomeAPIService someAPIService)
   {
      this.someAPIService = someAPIService;
   }

   @Override
   public void method1()
   {
      someAPIService.method1();
      System.out.println("We use new lib, great print1");
   }

   @Override
   public void method2()
   {
      someAPIService.method2();
      System.out.println("We use new lib, great print2");
   }

   @Override
   public void method3()
   {
      someAPIService.method3();
      System.out.println("We use new lib, great print3");
   }

   @Override
   public void newTotalPrintMethod()
   {
      someAPIService.method1();
      someAPIService.method2();
      someAPIService.method3();
      System.out.println("We use new lib, Absolutely new feature!!!!!");
   }
}
