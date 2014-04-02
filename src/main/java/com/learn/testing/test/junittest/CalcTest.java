package com.learn.testing.test.junittest;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by IntelliJ IDEA.
 * User: bilyk
 * Date: 26.07.12
 * Time: 17:06
 * To change this template use File | Settings | File Templates.
 */
public class CalcTest {
    Logger logger = Logger.getLogger(CalcTest.class);
    @Before
    public void printSomething(){
          logger.info("before");
    }
    
    
    @Test
    public void testAdd() {
        logger.info("in test method");
        assertEquals((int) 5, new Calc().add(2, 3));
    }
}