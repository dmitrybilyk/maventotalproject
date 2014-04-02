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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by dmitry on 3/16/14.
 */
public class ShowStudentsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<Integer, Student> students = StudentsDaoHolder.studentMap;

        List<Student> studentList = new ArrayList<Student>();
        for (Integer age : students.keySet()) {
            studentList.add(students.get(age));
        }
        req.setAttribute("students", studentList);
//        drawStudents(resp);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("students.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        drawStudents(resp);
    }

    private void drawStudents(HttpServletResponse resp){
        Map<Integer, Student> students = StudentsDaoHolder.studentMap;

        List<Student> studentList = new ArrayList<Student>();
        for (Integer age : students.keySet()) {
            studentList.add(students.get(age));
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

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        StudentsDaoHolder.studentMap.remove(req.getParameter("age"));
        drawStudents(resp);
    }
}
