package com.learn.testing.complextest.service.test.easymock;

import com.learn.testing.complextest.dao.api.TestedObjectDao;
import com.learn.testing.complextest.exceptions.InvalidateException;
import com.learn.testing.complextest.model.TestedObject;
import com.learn.testing.complextest.service.api.CheckingService;
import com.learn.testing.complextest.service.impl.TestedObjectServiceImpl;
import com.learn.testing.complextest.utils.TestedObjectUtils;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;

import static org.easymock.EasyMock.*;
import static org.junit.Assert.assertEquals;

/**
 * Created with IntelliJ IDEA.
 * User: Buh
 * Date: 30.08.13
 * Time: 12:33
 * To change this template use File | Settings | File Templates.
 */

@ContextConfiguration(locations = {"classpath*:testing/testing-context.xml" })
public class TestedObjectServiceImplTest {


    TestedObjectServiceImpl testedObjectService = new TestedObjectServiceImpl();

    TestedObjectDao dao = createNiceMock(TestedObjectDao.class);
    CheckingService checkingService = createMock(CheckingService.class);
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
        expect(dao.getTestedObject(isA(String.class))).andReturn(testedObject);
        checkingService.check("", 2, testedObject);
        expectLastCall();

        replay(dao);
        TestedObject object = testedObjectService.getTestedObjectByName(name);
        assertEquals(name, object.getName());
        verify(dao);
    }


    @Test
    public void testSalaryCalculation(){

    }
}
