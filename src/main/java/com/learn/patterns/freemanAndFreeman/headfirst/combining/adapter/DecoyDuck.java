package com.learn.patterns.freemanAndFreeman.headfirst.combining.adapter;

public class DecoyDuck implements Quackable {
	public void quack() {
		System.out.println("<< Silence >>");
	}
}
