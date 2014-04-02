package com.learn.patterns.behavioral.memento;

/**
 * Created with IntelliJ IDEA.
 * User: dmitriy.bilyk
 * Date: 11.11.13
 * Time: 21:28
 * To change this template use File | Settings | File Templates.
 */
//Memento pattern is implemented with two objects – Originator and Caretaker.
// Originator is the object whose state needs to be saved and restored and
// it uses an inner class to save the state of Object. The inner class is
// called Memento and its private, so that it can’t be accessed from other
// objects.
//    Caretaker is the helper class that is responsible for storing and
// restoring the Originator’s state through Memento object. Since Memento
// is private to Originator, Caretaker can’t access it and it’s stored as
// a Object within the caretaker.
public class FileWriterCaretaker {

    private Object obj;

    public void save(FileWriterUtil fileWriter){
        this.obj=fileWriter.save();
    }

    public void undo(FileWriterUtil fileWriter){
        fileWriter.undoToLastSave(obj);
    }
}
