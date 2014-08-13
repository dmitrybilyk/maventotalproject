package com.learn.patterns.structural.proxy;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: dmitriy.bilyk
 * Date: 12.11.13
 * Time: 22:43
 * To change this template use File | Settings | File Templates.
 */
public class CommandExecutorImpl implements CommandExecutor {

    @Override
    public void runCommand(String cmd) throws IOException {
        //some heavy implementation
        Process process = Runtime.getRuntime().exec(cmd);
       System.out.println(process.getOutputStream().toString());
        System.out.println("'" + cmd + "' command executed.");
    }

}