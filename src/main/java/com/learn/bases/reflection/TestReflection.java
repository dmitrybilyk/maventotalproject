package com.learn.bases.reflection;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by IntelliJ IDEA.
 * User: Flash
 * Date: 26.05.12
 * Time: 22:11
 * To change this template use File | Settings | File Templates.
 */
public class TestReflection {
    private int b;
    private int c;
    void testPrint(String s, int a){
        System.out.println(s+a);
    }
    
    
    
    public static void main(String[] args){
        TestReflection testReflection = new TestReflection();
        Class classTestReflection = testReflection.getClass();
        
        Field[] fields = classTestReflection.getDeclaredFields();
        for (Field field : fields) {
            System.out.println(field.getName());
        }
        
        Method[] methods = classTestReflection.getDeclaredMethods();
        for (Method method : methods) {
            System.out.println(method.getName());
        }

        try {
            Field f = testReflection.getClass().getDeclaredField("c");
            f.setAccessible(true);
            f.setInt(testReflection, 47);
            Class[] paramTypes = new Class[] { String.class, int.class };
            System.out.println(Class.forName(TestReflection.class.getName()));
            Method method = classTestReflection.getDeclaredMethod("testPrint", paramTypes);
            method.invoke(testReflection, new Object[]{new String("dd"), new Integer(20)});
            System.out.println(method.getReturnType());
        } catch (NoSuchMethodException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (InvocationTargetException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (IllegalAccessException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (NoSuchFieldException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (ClassNotFoundException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }
}
