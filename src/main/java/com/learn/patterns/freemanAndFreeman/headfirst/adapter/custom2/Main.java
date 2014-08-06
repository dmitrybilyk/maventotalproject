package com.learn.patterns.freemanAndFreeman.headfirst.adapter.custom2;

/**
 * Created by bid on 8/5/14.
 */
public class Main
{
   public static void main(String[] args)
   {
      SomeAPIService someAPIService = new SomeAPIServiceImpl();
      SomeClientService someClientService = new SomeClientServiceImpl(someAPIService);
      someClientService.clientMethod();

      System.out.println("------------------------------------------------------------------");
      System.out.println("Now we want to use new feature method!!!");

      SomeApiServiceAdapter someApiServiceAdapter = new SomeApiServiceAdapter(someAPIService);
      someApiServiceAdapter.newTotalPrintMethod();
   }
}
