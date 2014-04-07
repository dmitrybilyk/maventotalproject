package com.learn.testing.complextest.service.test.mockito;

import com.learn.testing.complextest.dao.api.TestedObjectDao;
import com.learn.testing.complextest.exceptions.InvalidateException;
import com.learn.testing.complextest.model.TestedObject;
import com.learn.testing.complextest.service.api.CheckingService;
import com.learn.testing.complextest.service.impl.TestedObjectServiceImpl;
import com.learn.testing.complextest.utils.TestedObjectUtils;
import com.learn.testing.mockito.cofeemaschineexample.data.Portion;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.VoidType;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import static org.junit.Assert.assertEquals;

/**
 * Created with IntelliJ IDEA.
 * User: Buh
 * Date: 30.08.13
 * Time: 12:33
 * To change this template use File | Settings | File Templates.
 */

@RunWith(MockitoJUnitRunner.class)
public class TestedObjectServiceImplTest {


    TestedObjectServiceImpl testedObjectService = new TestedObjectServiceImpl();

    @Mock
    TestedObjectDao dao;
    @Mock CheckingService checkingService;
    TestedObject testedObject;
    String name;

    @Before
    public void setUp(){
          testedObject = TestedObjectUtils.buildTestedObject();
          testedObjectService.setDao(dao);
          testedObjectService.setCheckingService(checkingService);
          name = testedObject.getName();
    }

    @Test
    public void testGettingOfTestedObject() throws InvalidateException {
        when(dao.getTestedObject(isA(String.class))).thenReturn(testedObject);
//        expect(dao.getTestedObject(isA(String.class))).andReturn(testedObject);
//        when(checkingService.check(anyString(), eq(2), testedObject))
//        checkingService.check("", 2, testedObject);
        verify(dao);
//        expectLastCall();

//        replay(dao);
        TestedObject object = testedObjectService.getTestedObjectByName(name);
        assertEquals(name, object.getName());
        verify(dao);
    }


    @Test
    public void testSalaryCalculation(){

    }
}
