package com.flash.model;

import java.util.List;

/**
 * Created by bid on 8/9/14.
 */
public class StudentResponse
{
   private List<Student> students;
   private String        errorMessage;
   private Boolean success = true;

   public List<Student> getStudents()
   {
      return students;
   }

   public void setStudents(List<Student> students)
   {
      this.students = students;
   }

   public Boolean isSuccess()
   {
      return success;
   }

   public void setSuccess(Boolean success)
   {
      this.success = success;
   }

   public String getErrorMessage()
   {
      return errorMessage;
   }

   public void setErrorMessage(String errorMessage)
   {
      this.errorMessage = errorMessage;
   }
}
