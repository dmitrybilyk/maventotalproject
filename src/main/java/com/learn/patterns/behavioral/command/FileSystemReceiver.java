package com.learn.patterns.behavioral.command;

/**
 * Created with IntelliJ IDEA.
 * User: dmitriy.bilyk
 * Date: 07.11.13
 * Time: 21:33
 * To change this template use File | Settings | File Templates.
 */
public interface FileSystemReceiver {

    void openFile();
    void writeFile();
    void closeFile();
}