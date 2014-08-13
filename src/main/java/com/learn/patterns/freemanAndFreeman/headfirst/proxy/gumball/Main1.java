package com.learn.patterns.freemanAndFreeman.headfirst.proxy.gumball;
import java.rmi.*;

public class Main1
{
 
	public static void main(String[] args) {
		GumballMachineRemote gumballMachine = null;
		int count;

//		if (args.length < 2) {
//			System.out.println("GumballMachine <name> <inventory>");
// 			System.exit(1);
//		}

		try {
			count = Integer.parseInt("3");

			gumballMachine = 
				new GumballMachine("santafe.mightygumball.com", count);
			Naming.rebind("//" + "santafe.mightygumball.com" + "/gumballmachine", gumballMachine);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
