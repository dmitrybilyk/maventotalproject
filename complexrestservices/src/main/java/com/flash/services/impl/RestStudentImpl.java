package com.flash.services.impl;

import com.flash.model.Student;
import com.flash.services.RestStudent;
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
   public Response createStudent()
   {
      return Response.ok(new Student("Dima", 33)).build();
   }
}
