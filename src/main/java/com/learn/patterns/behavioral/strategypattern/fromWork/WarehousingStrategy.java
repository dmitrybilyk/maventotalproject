package com.learn.patterns.behavioral.strategypattern.fromWork;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: dmitriy.bilyk
 * Date: 14.01.14
 * Time: 21:29
 * To change this template use File | Settings | File Templates.
 */
public interface WarehousingStrategy {
    public boolean isApplicable(Warehousing warehousing);
    public void executeCalculation(Date date);
}
