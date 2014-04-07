package com.learn.testing.mockito.cofeemaschineexample.mechanism;


import com.learn.testing.mockito.cofeemaschineexample.data.Portion;
import com.learn.testing.mockito.cofeemaschineexample.exceptions.NotEnoughException;
import com.learn.testing.mockito.cofeemaschineexample.mechanism.interfaces.IContainer;

public abstract class AbstractContainer implements IContainer {

	private int containerTotalVolume;
	private int currentVolume;

	public AbstractContainer(int volume) {
		if (volume < 1)
			throw new IllegalArgumentException("Container's value must be greater then 0.");
		containerTotalVolume = volume;
		currentVolume = volume;
	}

	@Override
	public boolean getPortion(Portion portion) throws NotEnoughException {
		int delta = currentVolume - portion.size();
		if (delta > -1) {
			currentVolume -= portion.size();
			return true;
		} else
			throw new NotEnoughException("Refill the "
					+ this.getClass().getName());
	}

	@Override
	public int getCurrentVolume() {
		return currentVolume;
	}

	@Override
	public int getTotalVolume() {
		return containerTotalVolume;
	}

	@Override
	public void refillContainer() {
		currentVolume = containerTotalVolume;
	}

}
