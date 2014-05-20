package com.learn.refactoring.firstPart;

/**
 * Created by dmitry on 21.05.14.
 */
public abstract class Price {
    public abstract int getPriceCode();

    public abstract double getCharge(int daysRented);

    public int getFrequentRenterPoints(int daysRented){
        return 1;
    }
}
