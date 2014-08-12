package com.learn.patterns.freemanAndFreeman.headfirst.composite.custom;

import java.util.Iterator;
import java.util.List;

/**
 * Created by dmitry on 8/11/14.
 */
public abstract class FileUnit {

    public void createFile(){
        throw new UnsupportedOperationException();
    }
    public void cleanDirectory(){
        throw new UnsupportedOperationException();
    }
    public void moveFile(){
        throw new UnsupportedOperationException();
    }

    public abstract void printInfo();

   public abstract Iterator createIterator();
}
