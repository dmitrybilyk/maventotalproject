package com.learn.bases.appacheutils.readingfiles.scannerusage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
 
public class ReadStreamIntoStringUsingScanner
{
    @SuppressWarnings("resource")
    public static void main(String[] args) throws FileNotFoundException, IOException
    {
        FileInputStream fin = new FileInputStream(new File("/home/dmitry/dev/projects/"+
                "totalmavenproject/src/main/java/com/learn/bases/appacheutils/readingfiles/"+
                "ordinaryreader/joins.txt"));
        java.util.Scanner scanner = new java.util.Scanner(fin,"UTF-8").useDelimiter("\\A");
        String theString = scanner.hasNext() ? scanner.next() : "";
        System.out.println(theString);
        scanner.close();
    }
}