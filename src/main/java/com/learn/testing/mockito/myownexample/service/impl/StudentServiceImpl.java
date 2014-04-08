package com.learn.testing.mockito.myownexample.service.impl;

import com.learn.testing.mockito.myownexample.dao.api.StudentDao;
import com.learn.testing.mockito.myownexample.model.Student;
import com.learn.testing.mockito.myownexample.service.api.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by dmitry.bilyk on 4/7/14.
 */
@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentDao studentDao;

    @Override
    public long countSalary(Student student) {
        return 0;
    }

    @Override
    public boolean addStudent(Student student) {
        return studentDao.addStudent(student);
    }

    @Override
    public List<Student> getAll() {
        return studentDao.getAll();
    }

    @Override
    public List<Student> getByName(String pattern) {
        return studentDao.getByName(pattern);
    }

    @Override
    public void publicStudentName(String name) {
        studentDao.publishDoubleName(name);
    }

    public void setStudentDao(StudentDao studentDao) {
        this.studentDao = studentDao;
    }
}
