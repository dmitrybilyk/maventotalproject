package com.learn.testing.complextest.utils;

import com.learn.testing.complextest.model.TestedObject;
import org.apache.commons.lang.RandomStringUtils;

import java.math.BigDecimal;

/**
 * Created with IntelliJ IDEA.
 * User: Buh
 * Date: 30.08.13
 * Time: 12:25
 * To change this template use File | Settings | File Templates.
 */
public class TestedObjectUtils {
    public static BigDecimal calculateSalaryForObject(long premiya, BigDecimal oklad){
          return oklad.multiply(new BigDecimal(100 + premiya).divide(new BigDecimal(100)));
    }
    public static TestedObject buildTestedObject(){
        return new TestedObject("name_"+RandomStringUtils.random(5), 30);
    }
}
