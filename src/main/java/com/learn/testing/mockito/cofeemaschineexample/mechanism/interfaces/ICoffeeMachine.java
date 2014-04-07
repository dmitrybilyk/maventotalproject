package com.learn.testing.mockito.cofeemaschineexample.mechanism.interfaces;


import com.learn.testing.mockito.cofeemaschineexample.data.Portion;
import com.learn.testing.mockito.cofeemaschineexample.exceptions.NotEnoughException;

public interface ICoffeeMachine {
	
	public boolean makeCoffee(Portion portion) throws NotEnoughException;
	public IContainer getCoffeeContainer();
	public IContainer getWaterContainer();

}
