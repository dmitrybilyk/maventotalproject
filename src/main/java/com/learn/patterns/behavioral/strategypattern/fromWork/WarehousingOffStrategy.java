package com.learn.patterns.behavioral.strategypattern.fromWork;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: dmitriy.bilyk
 * Date: 14.01.14
 * Time: 21:39
 * To change this template use File | Settings | File Templates.
 */
public class WarehousingOffStrategy extends DateCalculationServicePojo implements WarehousingStrategy {
    @Override
    public boolean isApplicable(Warehousing warehousing) {
        return warehousing == Warehousing.OFF;
    }

    @Override
    public void executeCalculation(Date date) {
        System.out.println("Date calculation from warehousing off strategy "+date.getTime());
    }
}
