package com.learn.patterns.freemanAndFreeman.headfirst.adapter.custom2;

/**
 * Created by bid on 8/5/14.
 */
public class SomeClientServiceImpl implements SomeClientService
{
   SomeAPIService someAPIService;

   public SomeClientServiceImpl(final SomeAPIService someAPIService)
   {
      this.someAPIService = someAPIService;
   }

   @Override
   public void clientMethod()
   {
      someAPIService.method1();
   }
}
