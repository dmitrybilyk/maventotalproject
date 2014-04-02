package com.learn.bases.exceptionstest.complextesting.dao;

import com.learn.bases.exceptionstest.complextesting.exception.SaveException;

/**
 * Created with IntelliJ IDEA.
 * User: Buh
 * Date: 30.08.13
 * Time: 19:31
 * To change this template use File | Settings | File Templates.
 */
public interface DaoService {
    public void save(int i) throws SaveException;
}
