package com.learn.bases.arraystest;

/**
 * Created by IntelliJ IDEA.
 * User: bilyk
 * Date: 24.05.12
 * Time: 19:55
 * To change this template use File | Settings | File Templates.
 */
public class MyOwnBulbeTestArraySorting {
    
    void sortedArray(int[] arrayToSort){
       
         for(int i = arrayToSort.length-1; i>=2; i--){
             boolean sorted = true;
             for(int j = 0; j<i; j++){
                 if(arrayToSort[j]>arrayToSort[j+1]){
                      int temp = arrayToSort[j];
                      arrayToSort[j] = arrayToSort[j+1];
                      arrayToSort[j+1] = temp;
                      sorted = false;
                  }
                 
            }
             if(sorted){
                 break;
             }

        }
        for (int i = 0; i < arrayToSort.length; i++) {
            System.out.println(arrayToSort[i]);
        }
    }
}
