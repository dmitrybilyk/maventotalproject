package com.learn.patterns.freemanAndFreeman.headfirst.composite.custom;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by dmitry on 8/11/14.
 */
public class Directory extends FileUnit {
    private String dirName;

    private ArrayList fileUnits;


    public void setFileUnits(ArrayList<FileUnit> fileUnits) {
        this.fileUnits = fileUnits;
    }

    public Directory() {
    }


    public String getDirName() {
        return dirName;
    }

    public void setDirName(String dirName) {
        this.dirName = dirName;
    }

    @Override
    public void createFile() {
        System.out.println("File is created");
    }

    @Override
    public void cleanDirectory() {
        System.out.println("Directory is cleaned");
    }

    @Override
    public void moveFile() {
        System.out.println("File's moved");
    }

    @Override
    public void printInfo() {
        System.out.println("Directory name - " + dirName);
       if(fileUnits != null){
          Iterator iterator = fileUnits.iterator();
          while (iterator.hasNext()) {
             FileUnit fileUnit =
                     (FileUnit)iterator.next();
             fileUnit.printInfo();
          }
       }
    }

   @Override
   public Iterator createIterator()
   {
      if(fileUnits != null){
         return new CompositeIterator(fileUnits.iterator());
      }
      return null;
   }
}
