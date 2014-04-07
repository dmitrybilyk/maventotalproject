package com.learn.testing.mockito.cofeemaschineexample.data;

public enum Portion {
	SMALL(1), MEDIUM(2), LARGE(3);
	
	private int size;
	
	private Portion(int size) {
		this.size = size;
	}
	
	public int size() {
		return size;
	}
}
