package com.learn.exercises;

/**
 * Created by dmitry on 8/13/14.
 */
public class FloatExercise {
    public static void main(String[] args) {
        Float f = new Float("12");
//        switch (f)
//        {
//            case 12: System.out.println("Twelve");
//            case 0: System.out.println("Zero");
//            default: System.out.println("Default");
//        }
    }
}

//Compilation error
//The switch statement can only be supported by integers or variables more "narrow" than an integer i.e.
// byte, char, short. Here a Float wrapper object is used and so the compilation fails.
