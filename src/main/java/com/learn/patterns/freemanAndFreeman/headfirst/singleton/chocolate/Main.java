package com.learn.patterns.freemanAndFreeman.headfirst.singleton.chocolate;
 
public class Main
{
	public static void main(String args[]) {
		ChocolateBoiler boiler = ChocolateBoiler.getInstance();
      System.out.println(boiler);
      boiler.fill();
		boiler.boil();
		boiler.drain();

		// will return the existing instance
		ChocolateBoiler boiler2 = ChocolateBoiler.getInstance();
      System.out.println(boiler2);
   }
}
