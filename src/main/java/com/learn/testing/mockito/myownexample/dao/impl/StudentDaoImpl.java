package com.learn.testing.mockito.myownexample.dao.impl;

import com.learn.testing.mockito.myownexample.dao.api.StudentDao;
import com.learn.testing.mockito.myownexample.model.Student;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dmitry.bilyk on 4/7/14.
 */
@Repository
public class StudentDaoImpl implements StudentDao {

    private static List<Student> studentList;

    {
        Student student = new Student("Nikita", 6);
        Student student2 = new Student("Dima", 32);
        Student student3 = new Student("Ruslan", 36);
        studentList = new ArrayList<Student>();
        studentList.add(student);
        studentList.add(student2);
        studentList.add(student3);
    }

    @Override
    public boolean addStudent(Student student) {
        studentList.add(student);
        boolean isPresent = false;
        for (Student student1 : studentList) {
            if(student.getName().equals(student1.getName())){
                isPresent = true;
                break;
            }
        }
        if(isPresent){
            return false;
        } else {
            studentList.add(student);
            return true;
        }
    }

    @Override
    public List<Student> getAll() {
        return studentList;
    }

    @Override
    public List<Student> getByName(String pattern) {
        List<Student> listToReturn = new ArrayList<Student>();
        for (Student student : studentList) {
            if(student.getName().contains(pattern)){
                listToReturn.add(student);
            }
        }
        return listToReturn;
    }

    @Override
    public void publishDoubleName(String name) {
        String doubleName = name+name;
        System.out.println(doubleName);
    }
}
