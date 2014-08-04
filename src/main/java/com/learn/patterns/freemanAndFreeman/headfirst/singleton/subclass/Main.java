package com.learn.patterns.freemanAndFreeman.headfirst.singleton.subclass;

public class Main
{
	public static void main(String[] args) {
		Singleton foo = CoolerSingleton.getInstance();
		Singleton bar = HotterSingleton.getInstance();
		System.out.println(foo);
		System.out.println(bar);
 	}
}
