package com.learn.bases.appacheutils.readingfiles.withapachestringwriter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringWriter;
 
import org.apache.commons.io.IOUtils;
 
public class ReadStreamIntoStringUsingIOUtils
{
    public static void main(String[] args) throws FileNotFoundException, IOException
    {
        //Method 1 IOUtils.copy()
         
        StringWriter writer = new StringWriter();
        IOUtils.copy(new FileInputStream(new File("/home/dmitry/dev/projects/"+
                "totalmavenproject/src/main/java/com/learn/bases/appacheutils/readingfiles/"+
                "ordinaryreader/joins.txt")), writer, "UTF-8");
        String theString = writer.toString();
        System.out.println(theString);
         
        //Method 2 IOUtils.toString()
         
        String theString2 = IOUtils.toString(new FileInputStream(new File("/home/dmitry/dev/projects/"+
                "totalmavenproject/src/main/java/com/learn/bases/appacheutils/readingfiles/"+
                "ordinaryreader/joins.txt")), "UTF-8");
        System.out.println(theString2);
    }
}