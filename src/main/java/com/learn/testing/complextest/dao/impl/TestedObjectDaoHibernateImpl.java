package com.learn.testing.complextest.dao.impl;

import com.learn.testing.complextest.dao.api.TestedObjectDao;
import com.learn.testing.complextest.model.TestedObject;
import com.learn.testing.complextest.utils.TestedObjectUtils;
import org.springframework.stereotype.Repository;

/**
 * Created with IntelliJ IDEA.
 * User: Buh
 * Date: 30.08.13
 * Time: 12:22
 * To change this template use File | Settings | File Templates.
 */
@Repository
public class TestedObjectDaoHibernateImpl implements TestedObjectDao {
    @Override
    public void save(TestedObject testedObject) {
        System.out.println("saving of testing object");
    }

    @Override
    public TestedObject getTestedObject(String name) {
        return TestedObjectUtils.buildTestedObject();
    }

    @Override
    public void printSomething() {
        System.out.println("print something");
    }
}
