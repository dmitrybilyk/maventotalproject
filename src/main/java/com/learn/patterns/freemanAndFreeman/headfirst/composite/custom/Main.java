package com.learn.patterns.freemanAndFreeman.headfirst.composite.custom;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dmitry on 8/11/14.
 */
public class Main {
    public static void main(String[] args) {



        RealFile realFileDir1 = new RealFile("file 1 dir 1", 345);
        RealFile realFileDir2 = new RealFile("file 2 dir 1", 3345);
        RealFile realFileDir3 = new RealFile("file 3 dir 1", 145);

        Directory subDir1 = new Directory();
        subDir1.setDirName("subdirname");
        RealFile realFileDir32 = new RealFile("file 1 sub dir 1", 33345);
        RealFile realFileDir33 = new RealFile("file 2 sub dir 1", 344345);
        ArrayList<FileUnit> realFilesSubDirList1 = new ArrayList<FileUnit>();
        realFilesSubDirList1.add(realFileDir32);
        realFilesSubDirList1.add(realFileDir33);
        subDir1.setFileUnits(realFilesSubDirList1);

        Directory fileUnit = new Directory();
        fileUnit.setDirName("dir name");
        ArrayList<FileUnit> realFilesDirList1 = new ArrayList<FileUnit>();
        realFilesDirList1.add(realFileDir1);
        realFilesDirList1.add(realFileDir2);
        realFilesDirList1.add(realFileDir3);
        realFilesDirList1.add(subDir1);
        fileUnit.setFileUnits(realFilesDirList1);

        FileClient fileClient = new FileClient(fileUnit);
        fileClient.listFiles();
    }
}
