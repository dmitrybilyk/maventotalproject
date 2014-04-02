package com.learn.controller.regularservlets;

import com.learn.model.ajax.Student;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by dmitry.bilyk on 4/1/14.
 */
public class StudentsDaoHolder {

    public static final Map<Integer, Student> studentMap ;
    static{
        Student student1 = new Student("Dimon", 32);
        Student student2 = new Student("Ruslan", 36);
        Student student3 = new Student("Andrey", 32);
        Student student4 = new Student("Sasha", 28);
        studentMap =  new HashMap<Integer, Student>();
        studentMap.put(student1.getAge(), student1);
        studentMap.put(student2.getAge(), student1);
        studentMap.put(student3.getAge(), student1);
        studentMap.put(student4.getAge(), student1);
    }
}
