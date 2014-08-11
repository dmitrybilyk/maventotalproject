package com.learn.patterns.freemanAndFreeman.headfirst.composite.custom;

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
}
