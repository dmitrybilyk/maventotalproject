package com.learn.testing.complextest.dao.api;

import com.learn.testing.complextest.model.TestedObject;

/**
 * Created with IntelliJ IDEA.
 * User: Buh
 * Date: 30.08.13
 * Time: 12:21
 * To change this template use File | Settings | File Templates.
 */
public interface TestedObjectDao {
    public void save(TestedObject testedObject);
    public TestedObject getTestedObject(String name);

    void printSomething();
}
