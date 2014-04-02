package com.learn.controller.regularservlets;

//import com.total.model.Student;

import com.learn.model.ajax.Student;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/**
 * Created by dmitry on 3/16/14.
 */
public class SaveStudentServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("students.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

            Integer age = Integer.valueOf(req.getParameter("age"));
            Student student = StudentsDaoHolder.studentMap.get(age);
            Student newStudent = new Student(student.getName(), student.getAge() + 50);

            StudentsDaoHolder.studentMap.put(newStudent.getAge(), newStudent);

        Map<Integer, Student> students = StudentsDaoHolder.studentMap;

        List<Student> studentList = new ArrayList<Student>();
        for (Integer age2 : students.keySet()) {
            studentList.add(students.get(age2));
        }

        ObjectMapper mapper = new ObjectMapper();


        try {

            // write JSON to a file
            mapper.writeValue(resp.getOutputStream(), studentList);

        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
