package com.learn.patterns.freemanAndFreeman.headfirst.decorator.custom;

/**
 * Created by bid on 7/31/14.
 */
public class Main
{
   public static void main(String[] args)
   {
      Room orderedRoom = new EconomRoom();
      orderedRoom = new ComfortableBath(orderedRoom);
      orderedRoom = new ComfortableTV(orderedRoom);
      orderedRoom = new ComfortableInternet(orderedRoom);
      System.out.println("The complex name of the order is: " + orderedRoom.getName());
      System.out.println("The total cost of the order is: " + orderedRoom.cost());

      Room luxRoom = new LuxRoom();
      luxRoom = new ComfortableMassage(luxRoom);
      luxRoom = new ComfortableMassage(luxRoom);
      System.out.println("The complex name of the order is: " + luxRoom.getName());
      System.out.println("The total cost of the order is: " + luxRoom.cost());

   }
}
