package com.learn.patterns.freemanAndFreeman.headfirst.composite.custom;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dmitry on 8/11/14.
 */
public class Main {
    public static void main(String[] args) {


       Directory rootDir = new Directory();
       rootDir.setDirName("root dir");

       ArrayList<FileUnit> fileUnits = new ArrayList<FileUnit>();

       Directory dirInDir1 = new Directory();

       RealFile realFileDir1 = new RealFile("file 1 dir 1", 345);
       RealFile realFileDir2 = new RealFile("file 2 dir 1", 3345);
       RealFile realFileDir3 = new RealFile("file 3 dir 1", 145);
       dirInDir1.setDirName("DirInDir1Name");

       fileUnits.add(realFileDir1);
       fileUnits.add(realFileDir2);
       fileUnits.add(realFileDir3);
       fileUnits.add(dirInDir1);

       rootDir.setFileUnits(fileUnits);

       Directory subDir = new Directory();
       subDir.setDirName("subDir dir");

       ArrayList<FileUnit> fileUnits2 = new ArrayList<FileUnit>();

       Directory dirInDir12 = new Directory();

       RealFile realFileDir12 = new RealFile("file 221 dir 1", 3245);
       RealFile realFileDir22 = new RealFile("file 222 dir 1", 32345);
       RealFile realFileDir32 = new RealFile("file 223 dir 1", 1245);
       dirInDir12.setDirName("DirInDir1Name");

       fileUnits2.add(realFileDir12);
       fileUnits2.add(realFileDir22);
       fileUnits2.add(realFileDir32);
       fileUnits2.add(dirInDir1);

       fileUnits.add(subDir);


       subDir.setFileUnits(fileUnits2);

        FileClient fileClient = new FileClient(rootDir);
        fileClient.listFiles();


        fileClient.listFilesWithIterator();
    }
}
