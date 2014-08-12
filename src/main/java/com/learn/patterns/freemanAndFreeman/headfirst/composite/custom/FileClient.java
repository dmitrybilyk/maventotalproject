package com.learn.patterns.freemanAndFreeman.headfirst.composite.custom;

import java.util.Iterator;
import java.util.List;

/**
 * Created by dmitry on 8/11/14.
 */
public class FileClient {
    private FileUnit fileUnit;

    public FileClient(FileUnit fileUnit) {
        this.fileUnit = fileUnit;
    }

    public void listFiles(){
        fileUnit.printInfo();
    }
    public void listFilesWithIterator(){
       Iterator iterator = fileUnit.createIterator();

       System.out.println("\nWith composite iterator\n----");
       while (iterator.hasNext()) {
          FileUnit fileUnit1 =
                  (FileUnit)iterator.next();
          try {
             fileUnit1.printInfo();
          } catch (UnsupportedOperationException e) {}
       }
    }
}
