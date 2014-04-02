package com.learn.bases.collections.hashcode;

/**
 * Created by IntelliJ IDEA.
 * User: bilyk
 * Date: 26.06.12
 * Time: 15:25
 * To change this template use File | Settings | File Templates.
 */

//Equal objects must produce the same hash code as long as they are equal, however unequal objects need not produce distinct hash codes.!!!

public class Test {
    private int age;
    private String name;
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

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public boolean equals(Object obj){
        if(obj==this){
            return true;
        }
        //not instance of (because instance of may return true if we compare class and subclass)
        if((obj==null)||obj.getClass()!=this.getClass()){
            return false;
        }
        Test test = (Test)obj;
        return age==test.getAge()&&height==test.getHeight()&&(name==test.name||(name!=null&&name.equals(test.name)));
    }


    public int hashCode(){
        int hash = 7;
        hash = 31 * hash + (null==name?0:name.hashCode());
        hash = 31 * hash + age;
        hash = 31 * hash + height;
        return hash;
    }
}
