package com.learn.patterns.behavioral.strategypattern.fromWork;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: dmitriy.bilyk
 * Date: 14.01.14
 * Time: 21:41
 * To change this template use File | Settings | File Templates.
 */
public class WarehousingOnReceiptStrategy extends DateCalculationServicePojo implements WarehousingStrategy {
    @Override
    public boolean isApplicable(Warehousing warehousing) {
        return warehousing == Warehousing.SEND_ON_FILE_RECEIPT;
    }

    @Override
    public void executeCalculation(Date date) {
        System.out.println("Date calculation from warehousing on receipt strategy "+date.getTime());
    }
}
