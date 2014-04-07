package com.learn.bases.threads.printNumbers;

public class threadDemo1 {

    public static void main(String[] args) {
        PrintSum s1 = new PrintSum(1, 100, true);
	PrintSum s2 = new PrintSum(1, 100, false);
	s1.start();
	s2.start();
	for (int k=1; k<=100; k++){
        System.out.println("in main");
    }

    }
}