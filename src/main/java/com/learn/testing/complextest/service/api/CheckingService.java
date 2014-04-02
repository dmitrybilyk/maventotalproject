package com.learn.testing.complextest.service.api;

import com.learn.testing.complextest.exceptions.InvalidateException;
import com.learn.testing.complextest.model.TestedObject;

/**
 * Created with IntelliJ IDEA.
 * User: Buh
 * Date: 30.08.13
 * Time: 14:04
 * To change this template use File | Settings | File Templates.
 */
public interface CheckingService {

    void check(String s, int i, TestedObject testedObject) throws InvalidateException;
}
