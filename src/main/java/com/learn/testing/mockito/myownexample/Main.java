package com.learn.testing.mockito.myownexample;

import com.learn.testing.mockito.myownexample.model.Student;
import com.learn.testing.mockito.myownexample.service.api.StudentService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * Created by dmitry.bilyk on 4/7/14.
 */
public class Main {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("mockitospringcontext/spring-context-mockito.xml");
        StudentService studentService = (StudentService) applicationContext.getBean("studentServiceImpl");
        List<Student> studentList = studentService.getAll();

        for (Student student : studentList) {
            System.out.println(student.getName());
        }
    }
}
