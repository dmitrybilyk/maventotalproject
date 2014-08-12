package com.learn.patterns.freemanAndFreeman.headfirst.composite.custom;

import java.util.Iterator;

/**
 * Created by dmitry on 8/11/14.
 */
public class RealFile extends FileUnit {
    private String name;
    private int size;

    public RealFile(String name, int size) {
        this.name = name;
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public void printInfo() {
        System.out.println("Name - " + name + ", size - " + size);
    }

   @Override
   public Iterator createIterator()
   {
      return new NullIterator();
   }
}
