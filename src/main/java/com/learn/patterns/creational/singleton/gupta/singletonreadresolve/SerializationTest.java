package com.learn.patterns.creational.singleton.gupta.singletonreadresolve;

import java.io.*;

/**
 * Created with IntelliJ IDEA.
 * User: dmitriy.bilyk
 * Date: 03.11.13
 * Time: 18:50
 * To change this template use File | Settings | File Templates.
 */
public class SerializationTest {
    static DemoSingleton instanceOne = DemoSingleton.getInstance();

    public static void main(String[] args) {
        try {
            // Serialize to a file
            ObjectOutput out = new ObjectOutputStream(new FileOutputStream(
                    "filename.ser"));
            out.writeObject(instanceOne);
            out.close();

            instanceOne.setI(20);

            // Serialize to a file
            ObjectInput in = new ObjectInputStream(new FileInputStream(
                    "filename.ser"));
            DemoSingleton instanceTwo = (DemoSingleton) in.readObject();
            in.close();

            System.out.println(instanceOne.getI());
            System.out.println(instanceTwo.getI());

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}


