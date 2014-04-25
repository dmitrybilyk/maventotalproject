package com.learn.bases.appacheutils.readingfiles.ordinaryreader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
 
public class ReadStreamIntoStringUsingReader
{
    public static void main(String[] args) throws FileNotFoundException, IOException
    {
        InputStream in = new FileInputStream(new File("/home/dmitry/dev/projects/"+
                "totalmavenproject/src/main/java/com/learn/bases/appacheutils/readingfiles/"+
                "ordinaryreader/joins.txt"));
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        StringBuilder out = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            out.append(line);
        }
        System.out.println(out.toString());   //Prints the string content read from input stream
        reader.close();
    }
}