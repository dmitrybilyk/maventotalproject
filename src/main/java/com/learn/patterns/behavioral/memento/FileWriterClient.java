package com.learn.patterns.behavioral.memento;

/**
 * Created with IntelliJ IDEA.
 * User: dmitriy.bilyk
 * Date: 11.11.13
 * Time: 21:29
 * To change this template use File | Settings | File Templates.
 */

//Memento design pattern is used when we want to save the state of an object
// so that we can restore later on. Memento pattern is used to implement
// this in such a way that the saved state data of the object is not
// accessible outside of the object, this protects the integrity of
// saved state data.
public class FileWriterClient {

    public static void main(String[] args) {

        FileWriterCaretaker caretaker = new FileWriterCaretaker();

        FileWriterUtil fileWriter = new FileWriterUtil("data.txt");
        fileWriter.write("First Set of Data\n");
        System.out.println(fileWriter+"\n\n");

        // lets save the file
        caretaker.save(fileWriter);
        //now write something else
        fileWriter.write("Second Set of Data\n");

        //checking file contents
        System.out.println(fileWriter+"\n\n");

        //lets undo to last save
        caretaker.undo(fileWriter);

        //checking file content again
        System.out.println(fileWriter+"\n\n");

    }

}