package com.learn.bases.iotest;

import java.io.*;

/**
 * Created by IntelliJ IDEA.
 * User: Flash
 * Date: 26.05.12
 * Time: 13:54
 * To change this template use File | Settings | File Templates.
 */
public class TestIO {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int i;
//        char c;
//        do {
//            c = (char) br.read();
//            System.out.println(c);
//        } while (c != ('q'));
        try{
            File file = new File("e:/java/answer.TXT");
            FileInputStream fis = new FileInputStream(file);
            File outputFile = new File("e:/java/outputFile.txt");
            FileOutputStream fos = new FileOutputStream(outputFile);
            do{
                i= fis.read();
                if(i!=-1)
                fos.write(i);
            }while(fis.read()!=-1);
            
            System.out.println(fis.read());
        }catch(FileNotFoundException ex){
            System.out.println("File Not Found");
        }


        
    }
}
