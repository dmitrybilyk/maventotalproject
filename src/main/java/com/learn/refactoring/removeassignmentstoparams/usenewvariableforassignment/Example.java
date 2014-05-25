package com.learn.refactoring.removeassignmentstoparams.usenewvariableforassignment;

/**
 * Created by dmitry on 5/25/14.
 */
public class Example {
//    int discount(int inputValue, int quantity, int yearToDate){
//        if(inputValue > 50){
//            inputValue -= 2;
//        }
//    }

//    instead you have to do:

//    int discount(int inputValue, int quantity, int yearToDate){
//        int result = inputValue;
//        if(inputValue > 50){
//            result  -= 2;
//        }
//    }

//    in order to check if parameter is changed add 'final' to this parameter

}
