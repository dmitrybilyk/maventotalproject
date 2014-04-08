package com.learn.testing.mockito.myownexample.service.api;

import com.learn.testing.mockito.myownexample.model.Student;

import java.util.List;

/**
 * Created by dmitry.bilyk on 4/7/14.
 */
public interface StudentService {
    public long countSalary(Student student);
    public boolean addStudent(Student student);
    public List<Student> getAll();
    public List<Student> getByName(String pattern);
    public void publicStudentName(String name);
}
