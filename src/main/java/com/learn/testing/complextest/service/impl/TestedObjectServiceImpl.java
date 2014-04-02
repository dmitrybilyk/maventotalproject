package com.learn.testing.complextest.service.impl;

import com.learn.testing.complextest.dao.api.TestedObjectDao;
import com.learn.testing.complextest.exceptions.InvalidateException;
import com.learn.testing.complextest.model.TestedObject;
import com.learn.testing.complextest.service.api.CheckingService;
import com.learn.testing.complextest.service.api.TestedObjectService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * User: Buh
 * Date: 30.08.13
 * Time: 12:30
 * To change this template use File | Settings | File Templates.
 */

@Service
public class TestedObjectServiceImpl implements TestedObjectService {

    @Autowired
    private TestedObjectDao dao;

    @Autowired
    private CheckingService checkingService;

    @Override
    public void saveTestedObject(TestedObject testedObject) {
        dao.save(testedObject);
    }

    private void printSomething() {
        System.out.println("test");
    }

    @Override
    public TestedObject getTestedObjectByName(String name) {
        Logger logger = Logger.getLogger(TestedObjectServiceImpl.class);
        TestedObject testedObject = dao.getTestedObject(name);
        try {
            checkingService.check("", 30, testedObject);
        } catch (InvalidateException e) {
            logger.debug("checking of object is failed!");
        }
        return testedObject;
    }

    public void setDao(TestedObjectDao dao) {
        this.dao = dao;
    }

    public void setCheckingService(CheckingService checkingService) {
        this.checkingService = checkingService;
    }
}
