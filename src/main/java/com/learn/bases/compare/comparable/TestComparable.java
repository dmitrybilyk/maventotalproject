package com.learn.bases.compare.comparable;

/**
 * Created by IntelliJ IDEA.
 * User: bilyk
 * Date: 07.05.12
 * Time: 16:49
 * To change this template use File | Settings | File Templates.
 */
public class TestComparable implements Comparable {
    int age;
    String name;

    public TestComparable(int i, String alex) {
        this.age = i;
        this.name = alex;
    }

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

    public int compareTo(Object o) {
        if(!(o instanceof TestComparable)){
             throw new ClassCastException();
        }

        int secondAge = ((TestComparable) o).getAge();
        String secondName = ((TestComparable) o).getName();

//        if(secondAge == this.age){
            return this.getName().compareTo(secondName);
//        }else{
//            return this.age - secondAge;
//        }
    }
}
