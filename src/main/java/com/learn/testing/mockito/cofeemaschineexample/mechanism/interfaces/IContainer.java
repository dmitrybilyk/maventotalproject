package com.learn.testing.mockito.cofeemaschineexample.mechanism.interfaces;


import com.learn.testing.mockito.cofeemaschineexample.data.Portion;
import com.learn.testing.mockito.cofeemaschineexample.exceptions.NotEnoughException;

public interface IContainer {
	
	public boolean getPortion(Portion portion) throws NotEnoughException;
	public int getCurrentVolume();
	public int getTotalVolume();
	public void refillContainer();

}
