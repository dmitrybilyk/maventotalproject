package com.learn.testing.mockito.cofeemaschineexample.mechanism;


import com.learn.testing.mockito.cofeemaschineexample.data.Portion;
import com.learn.testing.mockito.cofeemaschineexample.exceptions.NotEnoughException;
import com.learn.testing.mockito.cofeemaschineexample.mechanism.interfaces.ICoffeeMachine;
import com.learn.testing.mockito.cofeemaschineexample.mechanism.interfaces.IContainer;

public class CoffeeMachine implements ICoffeeMachine {

	private IContainer coffeeContainer;
	private IContainer waterContainer;

	public CoffeeMachine(IContainer cContainer, IContainer wContainer) {
		coffeeContainer = cContainer;
		waterContainer = wContainer;
	}

	@Override
	public boolean makeCoffee(Portion portion) throws NotEnoughException {
		
		boolean isEnoughCoffee = coffeeContainer.getPortion(portion);
		boolean isEnoughWater = waterContainer.getPortion(portion);
		
		if (isEnoughCoffee && isEnoughWater) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public IContainer getWaterContainer() {
		return waterContainer;
	}

	@Override
	public IContainer getCoffeeContainer() {
		return coffeeContainer;
	}

}
