package com.learn.bases.threads.callable.kumar.customcallable;

import java.util.concurrent.Callable;

/**
 * Created by bid on 8/18/14.
 */
public class StudentMultiply10Callable implements Callable<Student>
{
   @Override
   public Student call() throws Exception
   {
      Student student = new Student();
      student.setAge(24);
      student.setAge(student.countAgeMultiple10());
      return student;
   }
}
