package com.learn.refactoring.firstPart.pricechildren;

import com.learn.refactoring.firstPart.Movie;
import com.learn.refactoring.firstPart.Price;

public class RegularPrice extends Price {
    public int getPriceCode() {
        return Movie.REGULAR;
    }

    public double getCharge(int daysRented){
        double result = 2;
        if (daysRented > 2)
            result += (daysRented - 2) * 1.5;
        return result;
    }
}