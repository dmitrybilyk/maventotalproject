package com.learn.bases.exceptionstest.testexceptions;

import java.io.FileNotFoundException;

/**
 * Created by IntelliJ IDEA.
 * User: Flash
 * Date: 05.05.12
 * Time: 19:07
 * To change this template use File | Settings | File Templates.
 */
public class Main {
    public static void main(String[] args){
        Main main = new Main();
        try{
            main.evaluateZero();
        }catch (ArithmeticException ex){
             System.out.println("corrected again");
        }catch(FileNotFoundException ex){
            System.out.println("fof ex corrected");
        }catch (TestException ex){
             System.out.println(ex.getMessage());
        }
    }
    public void evaluateZero() throws FileNotFoundException, TestException{
        int d = 0;
        try{
            throw new ArithmeticException();

        }catch(ArithmeticException ex){
            System.out.println("corrected");
            throw new ArithmeticException();
        }
    }
}
