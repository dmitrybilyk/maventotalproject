package com.learn.bases.exceptionstest.complextesting.service;

import com.learn.bases.exceptionstest.complextesting.exception.SaveException;

/**
 * Created with IntelliJ IDEA.
 * User: Buh
 * Date: 30.08.13
 * Time: 19:34
 * To change this template use File | Settings | File Templates.
 */
public interface Service {
    public void save(int i) throws SaveException;
}
