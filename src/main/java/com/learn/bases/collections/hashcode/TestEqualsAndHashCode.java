package com.learn.bases.collections.hashcode;

/**
 * Created by IntelliJ IDEA.
 * User: bilyk
 * Date: 27.06.12
 * Time: 14:06
 * To change this template use File | Settings | File Templates.
 */
public class TestEqualsAndHashCode {
    private int age;
    private String name;
    private String surName;
    private int height;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
    
    public boolean equals(Object obj){
        if(obj==this)
            return true;
        if(obj!=null&&this.getClass()!=obj.getClass()){
            return false;
        }
        TestEqualsAndHashCode test = (TestEqualsAndHashCode) obj;
        return this.age==test.age&&(test.name==this.name||test.name.equals(this.name))&&this.height==test.height&&(test.surName==this.surName||test.surName.equals(this.surName));
    }
    
    public int hashCode(){
       int hash = 7;
       hash = hash * 31 + age;
       hash = hash * 31 + height;
       hash = hash * 31 + (this.name==null?0:name.hashCode());
       hash = hash * 31 + (this.surName==null?0:surName.hashCode());
       return hash;
    }
    
}
