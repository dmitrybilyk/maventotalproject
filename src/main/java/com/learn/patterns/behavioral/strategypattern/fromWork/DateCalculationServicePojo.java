package com.learn.patterns.behavioral.strategypattern.fromWork;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: dmitriy.bilyk
 * Date: 14.01.14
 * Time: 21:28
 * To change this template use File | Settings | File Templates.
 */
public class DateCalculationServicePojo {
    private List<WarehousingStrategy> warehousingStrategies;
    private Map<String, WarehousingStrategy> strategiesMap;

    public void calculateDates(Date now, Warehousing warehousing, ImportType importType){

        if(importType == ImportType.UPLOAD){
            WarehousingStrategy warehousingStrategy = strategiesMap.get(warehousing.name());
            warehousingStrategy.executeCalculation(now);
//            for (WarehousingStrategy strategy : warehousingStrategies) {
//                if(strategy.isApplicable(warehousing)){
//                    strategy.executeCalculation(now);
//                }
//            }
        } else {
            calculateForUI();
        }
    }

    private void calculateForUI(){
        System.out.println("for UI");
    }

    public void setWarehousingStrategies(List<WarehousingStrategy> warehousingStrategies) {
        this.warehousingStrategies = warehousingStrategies;
    }

    public void setStrategiesMap(Map strategiesMap) {
        this.strategiesMap = strategiesMap;
    }
}
