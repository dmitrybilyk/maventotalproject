package com.learn.bases.threads.callable.kumar.customcallable;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * Created by bid on 8/18/14.
 */
public class Main
{
   public static void main(String[] args)
   {
      StudentMultiply10Callable studentMultiply10Callable = new StudentMultiply10Callable();
      StudentMultiply10Callable studentMultiply10Callable2 = new StudentMultiply10Callable();
      StudentMultiply10Callable studentMultiply10Callable3 = new StudentMultiply10Callable();

      FutureTask<Student> futureTask1 = new FutureTask<Student>(studentMultiply10Callable);
      FutureTask<Student> futureTask2 = new FutureTask<Student>(studentMultiply10Callable2);
      FutureTask<Student> futureTask3 = new FutureTask<Student>(studentMultiply10Callable3);

      ExecutorService executor = Executors.newFixedThreadPool(3);

      executor.execute(futureTask1);
      executor.execute(futureTask2);
      executor.execute(futureTask3);

      while (true) {
         try {
            if(futureTask1.isDone() && futureTask2.isDone()){
               System.out.println("Done");
               //shut down executor service
               executor.shutdown();
               return;
            }

            if(!futureTask1.isDone()){
               //wait indefinitely for future task to complete
               System.out.println("FutureTask1 output="+futureTask1.get());
            }

            System.out.println("Waiting for FutureTask2 to complete");
            Student s = futureTask2.get(200L, TimeUnit.MILLISECONDS);
            if(s !=null){
               System.out.println("FutureTask2 output="+s);
            }
         } catch (InterruptedException ie) {
            ie.printStackTrace();
         }catch(TimeoutException te){
            //do nothing
         }catch(ExecutionException e){
            //do nothing
         }
      }


   }
}
