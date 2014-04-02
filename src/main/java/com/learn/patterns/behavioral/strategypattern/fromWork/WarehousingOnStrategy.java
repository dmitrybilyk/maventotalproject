package com.learn.patterns.behavioral.strategypattern.fromWork;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: dmitriy.bilyk
 * Date: 14.01.14
 * Time: 21:32
 * To change this template use File | Settings | File Templates.
 */
public class WarehousingOnStrategy extends DateCalculationServicePojo implements WarehousingStrategy {
    @Override
    public boolean isApplicable(Warehousing warehousing) {
        if(warehousing == Warehousing.ON){
            return true;
        }
        return false;
    }

    @Override
    public void executeCalculation(Date date) {
        System.out.println("Date calculation from warehousing on strategy "+date.getTime());
    }
}
