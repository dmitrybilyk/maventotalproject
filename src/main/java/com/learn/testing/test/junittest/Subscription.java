package com.learn.testing.test.junittest;

/**
 * Created by IntelliJ IDEA.
 * User: bilyk
 * Date: 26.07.12
 * Time: 15:49
 * To change this template use File | Settings | File Templates.
 */
public class Subscription {

    private int price ; // subscription total price in euro-cent
    private int length ; // length of subscription in months

    // constructor :
    public Subscription(int p, int n) {
        price = p ;
        length = n ;
    }

    /**
     * Calculate the monthly subscription price in euro,
     * rounded up to the nearest cent.
     */
    public double pricePerMonth() {
        double r = (double) price / (double) length ;
        return r ;
    }

    /**
     * Call this to cancel/nulify this subscription.
     */
    public void cancel() { length = 0 ; }

}
