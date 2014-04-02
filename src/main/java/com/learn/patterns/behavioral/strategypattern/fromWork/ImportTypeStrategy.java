package com.learn.patterns.behavioral.strategypattern.fromWork;

/**
 * Created with IntelliJ IDEA.
 * User: dmitriy.bilyk
 * Date: 14.01.14
 * Time: 22:03
 * To change this template use File | Settings | File Templates.
 */
public abstract class ImportTypeStrategy {
       abstract boolean isApplicable(ImportType importType);
}
