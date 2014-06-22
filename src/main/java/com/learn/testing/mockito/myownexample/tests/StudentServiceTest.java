package com.learn.testing.mockito.myownexample.tests;

/**
 * Created by dmitry.bilyk on 4/7/14.
 */
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import com.learn.testing.mockito.myownexample.dao.api.StudentDao;
import com.learn.testing.mockito.myownexample.dao.impl.StudentDaoImpl;
import com.learn.testing.mockito.myownexample.model.Student;
import com.learn.testing.mockito.myownexample.service.api.StudentService;
import com.learn.testing.mockito.myownexample.service.impl.StudentServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.mockito.verification.VerificationMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@ContextConfiguration(locations = {"classpath*:testing/spring-context-mockito.xml" })
public class StudentServiceTest {

    private StudentServiceImpl sut;

    @Mock StudentDao studentDaoMock;

    @Before
    public void setUp() throws Exception{
        MockitoAnnotations.initMocks( this );
        sut = new StudentServiceImpl();
        sut.setStudentDao(studentDaoMock);
    }

    @Test
    public void testGetAll(){
//        when(studentDaoMock.getAll()).thenReturn(createListOfStudent());
        when(studentDaoMock.getAll()).thenAnswer(new Answer<List<Student>>() {
            @Override
            public List<Student> answer(InvocationOnMock invocation) throws Throwable {
                Object[] args = invocation.getArguments();
                assertNotNull(invocation.getArguments());
                return createListOfStudent();
//                return "fuck";
            }
        });
        assertEquals(sut.getAll().size(), 3);
    }


    @Test
    public void testPublicName(){
//        doAnswer(new Answer() {
//            public Object answer(InvocationOnMock invocation) {
//                Object[] args = invocation.getArguments();
//                System.out.println(args[0]);
////              Mock com.cgi.pacoshop.mock = invocation.getMock();
//                return null;
//            }}).when(studentDaoMock).publishDoubleName("nameToPrint");

//            doCallRealMethod().when(studentDaoMock.publishDoubleName("ddd"));
//
//            sut.publicStudentName("dd");


//        when(studentDaoMock.publishDoubleName("name")).thenAnswer(new Answer<Object>() {
//            @Override
//            public Object answer(InvocationOnMock invocation) throws Throwable {
//                return null;
//            }
//        });
    }

    @Test
    public void testSpy(){
        StudentDao studentDao = new StudentDaoImpl();
        StudentDao spyDao = spy(studentDao);

        spyDao.publishDoubleName("tt");

        verify(spyDao).publishDoubleName("tt");
//        List list = new LinkedList();
//        List spy = spy(list);
//
////optionally, you can stub out some methods:
//        when(spy.size()).thenReturn(100);
//
////using the spy calls *real* methods
//        spy.add("one");
//        spy.add("two");
//
////prints "one" - the first element of a list
//        System.out.println(spy.get(0));
//
////size() method was stubbed - 100 is printed
//        System.out.println(spy.size());
//
////optionally, you can verify
//        verify(spy).add("one");
//        verify(spy).add("two");

    }




    private List<Student> createListOfStudent(){

        List<Student> studentList = new ArrayList<Student>();
        Student student = new Student("Nikita", 6);
        Student student2 = new Student("Dima", 32);
        Student student3 = new Student("Ruslan", 36);
        studentList.add(student);
        studentList.add(student2);
        studentList.add(student3);

        return studentList;
    }

}
