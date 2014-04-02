package com.learn.bases.reflection.tutorial;

import java.lang.reflect.Field;

/**
 * Created with IntelliJ IDEA.
 * User: dmitriy.bilyk
 * Date: 15.10.13
 * Time: 21:18
 * To change this template use File | Settings | File Templates.
 */
public class Main {
    public static void main(String[] args) {
        User user = new User(33, "Dima");
        System.out.println(user.getClass());
        try {
            Field field = user.getClass().getDeclaredField("age");
            field.setAccessible(true);
            Integer value = (Integer)field.get(user);
            System.out.println("value "+value);
//            If you don't know the name at compile time, but have the
//              class name as a string at runtime, you can do like this:
            System.out.println(Class.forName("com.learn.bases.reflection.tutorial.User").getSimpleName());
            System.out.println("Package "+Class.forName("com.learn.bases.reflection.tutorial.User").getPackage());
            System.out.println("SuperClass "+Class.forName("com.learn.bases.reflection.tutorial.User").getSuperclass());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (IllegalAccessException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }
}
