package com.learn.testing.complextest.service.impl;

import com.learn.testing.complextest.model.TestedObject;
import com.learn.testing.complextest.service.api.CheckingService;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * User: Buh
 * Date: 30.08.13
 * Time: 14:05
 * To change this template use File | Settings | File Templates.
 */

@Service
public class CheckingServiceImpl implements CheckingService {
    @Override
    public void check(String s, int i, TestedObject testedObject) {
        System.out.println("checking");
    }
}
