package com.learn.bases.log4jTest.logforjTest;

import org.apache.log4j.Logger;

/**
 * Created by IntelliJ IDEA.
 * User: Flash
 * Date: 23.05.12
 * Time: 21:59
 * To change this template use File | Settings | File Templates.
 */
public class LogForJTest {
    public static Logger LOG = Logger.getLogger(LogForJTest.class);
    public static void main(String[] args){
        LOG.info("Hello, logger");
        LOG.info("newSample debug message");
        LOG.info("Sample info message");
        LOG.warn("Sample warn message");
        LOG.error("Sample error message");
        LOG.fatal("Sample fatal message");
    }
}
