package com.learn.refactoring.firstPart.pricechildren;

import com.learn.refactoring.firstPart.Movie;
import com.learn.refactoring.firstPart.Price;

public class NewReleasePrice extends Price {
    public int getPriceCode() {
        return Movie.NEW_RELEASE;
    }

    public double getCharge(int daysRented){
        return daysRented * 3;
    }

    public int getFrequentRenterPoints(int daysRented) {
        return (daysRented > 1) ? 2: 1;
    }
}