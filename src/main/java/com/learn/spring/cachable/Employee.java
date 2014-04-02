package com.learn.spring.cachable;

import org.springframework.cache.annotation.Cacheable;

/**
 * Created by dmitry on 1/29/14.
 */
public class Employee {
    @Cacheable("emp")
    public String getEmployee(int empId){
        System.out.println("execute method..");
        if(empId==1){
            return "Atul";
        }else{
            return "Sudhir";
        }
    }
}