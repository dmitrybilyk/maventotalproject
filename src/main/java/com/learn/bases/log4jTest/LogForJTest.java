package com.learn.bases.log4jTest;

import org.apache.log4j.Logger;

/**
 * Created by IntelliJ IDEA.
 * User: bilyk
 * Date: 23.05.12
 * Time: 19:17
 * To change this template use File | Settings | File Templates.
 */
public class LogForJTest {
    public static final Logger LOG=Logger.getLogger(LogForJTest.class.getName());
    public static void main(String[] args){
           LOG.info("Hello, World!");
//        System.out.println(LOG.isLoggable(Level.INFO));
    }
}
