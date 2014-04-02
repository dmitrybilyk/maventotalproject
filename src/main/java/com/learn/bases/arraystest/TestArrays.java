package com.learn.bases.arraystest;

/**
 * Created by IntelliJ IDEA.
 * User: bilyk
 * Date: 24.05.12
 * Time: 17:49
 * To change this template use File | Settings | File Templates.
 */
public class TestArrays {
    public static void main(String[] args){
        int[] intArray = new int[19];
        for(int i=0; i<intArray.length; i++){
            intArray[i] = (int) Math.random()*10;
        }

        int[] newArray = new int[19];

        for (int i=0; i<intArray.length; i++) {
            if(intArray[i]%2==0){
                newArray[i]= intArray[i];
            }
        }

        for (int i = 0; i < newArray.length; i++) {
            if(newArray[i]!=0)
            System.out.print(newArray[i] + " ");
        }
        
        MultiArraysTest multiArraysTest = new MultiArraysTest();
        int[][][] multiArray = multiArraysTest.createMultiArray();
        for (int[][] ints : multiArray) {
            for (int[] anInt : ints) {
                System.out.println(anInt.length);
            }
        }

//        TestArrays3 testArrays = new TestArrays3();
//        testArrays.printSortedArray();

       MyOwnBulbeTestArraySorting testArrays = new MyOwnBulbeTestArraySorting();
        testArrays.sortedArray(new int[]{2, 5, 7, 3, 12, 1});

//        for (int i = 0; i < sortedArray.length; i++) {
//            System.out.println(sortedArray[i]);
//        }
        
    }
    void printSortedArray(){
         int[] arrayToSort = {2, 5, 7, 3, 12, 1};
         BulbeSortTest bulbeSortTest = new BulbeSortTest();
        bulbeSortTest.printSortedArray(arrayToSort);

    }
}
