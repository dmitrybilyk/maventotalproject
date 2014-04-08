package com.learn.testing.mockito.myownexample.dao.api;

import com.learn.testing.mockito.myownexample.model.Student;

import java.util.List;

/**
 * Created by dmitry.bilyk on 4/7/14.
 */
public interface StudentDao {
    public boolean addStudent(Student student);
    public List<Student> getAll();
    List<Student> getByName(String pattern);

    void publishDoubleName(String name);
}
