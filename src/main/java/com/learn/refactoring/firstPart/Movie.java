package com.learn.refactoring.firstPart;

import com.learn.refactoring.firstPart.pricechildren.ChildrensPrice;
import com.learn.refactoring.firstPart.pricechildren.NewReleasePrice;
import com.learn.refactoring.firstPart.pricechildren.RegularPrice;

public class Movie {
    public static final int CHILDRENS = 2;
    public static final int REGULAR = 0;
    public static final int NEW_RELEASE = 3;
    private String _title;
    private int _priceCode;
    private Price price;

    public Movie(String title, int priceCode) {
        _title = title;
        setPriceCode(priceCode);
    }

    public int getPriceCode() {
//        return _priceCode;
        return price.getPriceCode();
    }

    public void setPriceCode(int arg) {
        switch (arg) {
            case REGULAR:
                price = new RegularPrice();
                break;
            case CHILDRENS:
                price = new ChildrensPrice();
                break;
            case NEW_RELEASE:
                price = new NewReleasePrice();
                break;
            default:
                throw new IllegalArgumentException("Incorrect Price Code");
        }
    }

    public String getTitle() {
        return _title;
    }

    public Price getPrice() {
        return price;
    }

    public void setPrice(Price price) {
        this.price = price;
    }

    double getCharge(int daysRented) {
        return price.getCharge(daysRented);
    }

//    public double getCharge(int daysRented) {
//        double result = 0;
//        switch (getPriceCode()) {
//            case Movie.REGULAR:
//                result += 2;
//                if (daysRented > 2)
//                    result += (daysRented - 2) * 1.5;
//                break;
//            case Movie.NEW_RELEASE:
//                result += daysRented * 3;
//                break;
//            case Movie.CHILDRENS:
//                result += 1.5;
//                if (daysRented > 3)
//                    result += (daysRented - 3) * 1.5;
//                break;
//        }
//        return result;
//    }

    int getFrequentRenterPoints(int daysRented) {
        return price.getFrequentRenterPoints(daysRented);
    }

//    int getFrequentRenterPoints(int daysRented) {
//        if ((getPriceCode() == Movie.NEW_RELEASE) && daysRented > 1)
//            return 2;
//        else
//            return 1;
//    }

}