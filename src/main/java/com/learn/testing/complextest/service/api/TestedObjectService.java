package com.learn.testing.complextest.service.api;

import com.learn.testing.complextest.model.TestedObject;

/**
 * Created with IntelliJ IDEA.
 * User: Buh
 * Date: 30.08.13
 * Time: 12:24
 * To change this template use File | Settings | File Templates.
 */
public interface TestedObjectService {
    public void saveTestedObject(TestedObject testedObject);
    public TestedObject getTestedObjectByName(String name);
}
