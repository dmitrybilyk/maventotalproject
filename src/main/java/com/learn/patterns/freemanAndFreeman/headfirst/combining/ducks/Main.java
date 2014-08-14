package com.learn.patterns.freemanAndFreeman.headfirst.combining.ducks;

public class Main
{
	public static void main(String[] args) {
		Main simulator = new Main();
		simulator.simulate();
	}
  
	void simulate() {
		Quackable mallardDuck = new MallardDuck();
		Quackable redheadDuck = new RedheadDuck();
		Quackable duckCall = new DuckCall();
		Quackable rubberDuck = new RubberDuck();
		Quackable decoyDuck = new DecoyDuck();

		System.out.println("\nDuck Simulator");
 
		simulate(mallardDuck);
		simulate(redheadDuck);
		simulate(duckCall);
		simulate(rubberDuck);
      simulate(decoyDuck);
	}
   
	void simulate(Quackable duck) {
		duck.quack();
	}
}
