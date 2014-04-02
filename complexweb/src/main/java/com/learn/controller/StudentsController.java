package com.learn.controller;

import com.learn.controller.regularservlets.StudentsDaoHolder;
import com.learn.model.ajax.Student;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
//import org.springframework.web.servlet.View;
//import org.springframework.web.servlet.view.JstlView;

import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by dmitry.bilyk on 4/1/14.
 */
@Controller
@RequestMapping(value = "/students")
public class StudentsController {


    @RequestMapping(method = RequestMethod.GET)
    public String goToStudentsMVC(){
        return "studentsmvc";
    }


    @RequestMapping(value = "/ShowStudents")
    @ResponseBody
    public List<Student> showStudents(ModelAndView modelAndView){
        List<Student> studentList = new ArrayList<Student>();
        Student student1 = new Student("Dimon", 32);
        Student student2 = new Student("Ruslan", 36);
        Student student3 = new Student("Andrey", 32);
        Student student4 = new Student("Sasha", 28);
        studentList.add(student1);
        studentList.add(student2);
        studentList.add(student3);
        studentList.add(student4);
//        View view = new JstlView("students");
//        modelAndView.setView(view);
        return studentList;
    }

    @RequestMapping(value = "/SaveStudent")
    @ResponseBody
    @Path("age")
    public List<Student> saveStudent(@PathParam("age") int age){
        Student student = StudentsDaoHolder.studentMap.get(age);
        Student newStudent = new Student(student.getName(), student.getAge() + 50);

        StudentsDaoHolder.studentMap.put(newStudent.getAge(), newStudent);

        Map<Integer, Student> students = StudentsDaoHolder.studentMap;

        List<Student> studentList = new ArrayList<Student>();
        for (Integer age2 : students.keySet()) {
            studentList.add(students.get(age2));
        }

            return studentList;
    }
}
