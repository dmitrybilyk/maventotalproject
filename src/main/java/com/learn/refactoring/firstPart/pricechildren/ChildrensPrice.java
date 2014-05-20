package com.learn.refactoring.firstPart.pricechildren;

import com.learn.refactoring.firstPart.Movie;
import com.learn.refactoring.firstPart.Price;

/**
 * Created by dmitry on 21.05.14.
 */
public class ChildrensPrice extends Price {
    public int getPriceCode() {
        return Movie.CHILDRENS;
    }

    public double getCharge(int daysRented){
        double result = 1.5;
        if (daysRented > 3)
            result += (daysRented - 3) * 1.5;
        return result;
    }
}
