package com.learn.testing.mockito.myownexample.tests;

/**
 * Created by dmitry.bilyk on 4/7/14.
 */
import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

import com.learn.testing.mockito.myownexample.dao.api.StudentDao;
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
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
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
