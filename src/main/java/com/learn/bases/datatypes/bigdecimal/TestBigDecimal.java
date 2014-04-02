package com.learn.bases.datatypes.bigdecimal;

import java.math.BigDecimal;

public class TestBigDecimal {
    public static void main(String[] args) {
        String amountString = "-560.043";
        Double bigDecimal2 = new BigDecimal(amountString).movePointLeft(2)
                .add(new BigDecimal(40)).max(new BigDecimal(5)).subtract(new BigDecimal(4))
                .pow(3).doubleValue();
                ;
        System.out.println(bigDecimal2);
        System.out.println(bigDecimal2 instanceof Double?"true":"false");
        BigDecimal bigDecimal =  new BigDecimal(0.55);
        if(bigDecimal.compareTo(new BigDecimal(0))<0){
            System.out.println("fail");
        }else{
            System.out.println("success");
        }
//        Double.compare()
    }
}
