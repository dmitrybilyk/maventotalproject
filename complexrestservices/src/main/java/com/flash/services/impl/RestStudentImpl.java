package com.flash.services.impl;

import com.flash.model.Student;
import com.flash.model.StudentResponse;
import com.flash.services.RestStudent;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

/**
 * Created by bid on 8/5/14.
 */
public class RestStudentImpl implements RestStudent
{
   @Override
   public void printStudent()
   {
      System.out.println("test print student");
   }

   @Override
   public StudentResponse createStudent()
   {
      StudentResponse studentResponse = new StudentResponse();
      List<Student> studentList = new ArrayList<Student>();
      studentList.add(new Student("Dima", 33));
      studentResponse.setStudents(studentList);
      return studentResponse;
   }

   @Override
   public String createStudentJson()
   {
      return "{'friends': ['Michael', 'Tom', 'Daniel', 'John', 'Nick']}";
   }
}
