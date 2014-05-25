//package com.learn.refactoring.replaceMethodCallToObjectOfMethod;
//
///**
// * Created by dmitry on 5/25/14.
// */
//public class Account {
////    int gamma(int inputVal, int quantity, int yearToDate){
////        int importantValue1 = (inputVal * quantity) + delta();
////        int importantValue2 = (inputVal * yearToDate) + 100;
////        if((yearToDate - importantValue1) > 100){
////           importantValue2 -= 20;
////            int importantValue3 = importantValue2 * 7;
////            return importantValue3 - 2 * importantValue1;
////        }
////    }
//
////    and we replace this old method with
//
//    int gamma(int inputVal, int quantity, int yearToDate){
//        return new Gamma(this, inputVal, quantity, yearToDate).compute();
//    }
//

//and in 'compute' method we can use extraction of method
//
//}
//
////    firstly we have to create Class
//
//class Gamma{
//    private Account account;
//    private int inputVal;
//    private int quantity;
//    private int yearToDate;
//    private int importantValue1;
//    private int importantValue2;
//    private int importantValue3;
//
//    Gamma(Account source, int inputVal, int quantity, int yearToDate, int importantValue1, int importantValue2, int importantValue3) {
//        this.account = source;
//        this.inputVal = inputVal;
//        this.quantity = quantity;
//        this.yearToDate = yearToDate;
//        this.importantValue1 = importantValue1;
//        this.importantValue2 = importantValue2;
//        this.importantValue3 = importantValue3;
//    }
//
//
//    int compute(int inputVal, int quantity, int yearToDate){
//        int importantValue1 = (inputVal * quantity) + account.delta();
//        int importantValue2 = (inputVal * yearToDate) + 100;
//        if((yearToDate - importantValue1) > 100){
//            importantValue2 -= 20;
//            int importantValue3 = importantValue2 * 7;
//            return importantValue3 - 2 * importantValue1;
//        }
//    }
//
//}
