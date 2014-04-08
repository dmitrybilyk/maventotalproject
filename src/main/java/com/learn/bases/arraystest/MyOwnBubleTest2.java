package com.learn.bases.arraystest;

/**
 * Created by dmitry.bilyk on 4/8/14.
 */
public class MyOwnBubleTest2 {
    public static void main(String[] args) {
        int[] array = {5, 2, 4, 1, 9};
        MyOwnBubleTest2 myOwnBubleTest2 = new MyOwnBubleTest2();
//        myOwnBubleTest2.bubbleSortAlgorythm(array);
        myOwnBubleTest2.shellSort(array);
//        myOwnBubleTest2.countingSortAlgorithm(array);
        printSortedArray(array);
    }

    public int[] bubbleSortAlgorythm(int[] array){


        for (int i = array.length - 1; i > 0; i--) {
            boolean sorted = true;
            for(int j = 0; j < i; j++){
                if(array[j] > array[j+1]){
                    int temp = array[j];
                    array[j] = array[j+1];
                    array[j+1] = temp;
                    sorted = false;
                }
            }
            if(sorted){
                break;
            }
        }

       return array;
    }

//    Shell sort is a generalised form of insertion sort algorithm which
// improved the performance of insertion sort.
    public int[] shellSort(int[] data){
        int lenD = data.length;
        int inc = lenD/2;
        while(inc>0){
            for(int i=inc;i<lenD;i++){
                int tmp = data[i];
                int j = i;
                while(j>=inc && data[j-inc]>tmp){
                    data[j] = data[j-inc];
                    j = j-inc;
                }
                data[j] = tmp;
            }
            inc = (inc /2);
        }
        return data;
    }



    public int[] countingSortAlgorithm(int[] numbers){
            int max = numbers[0];
            for (int i = 1; i < numbers.length; i++) {
                if (numbers[i] > max)
                    max = numbers[i];
            }

            int[] sortedNumbers = new int[max+1];

            for (int i = 0; i < numbers.length; i++) {
                sortedNumbers[numbers[i]]++;
            }

            int insertPosition = 0;

            for (int i = 0; i <= max; i++) {
                for (int j = 0; j < sortedNumbers[i]; j++) {
                    numbers[insertPosition] = i;
                    insertPosition++;
                }
            }
            return numbers;
        }

    public static void printSortedArray(int[] array){
        for (int i : array) {
            System.out.println(i);
        }
    }


//    Insertion sort is a comparison sort algorithm which works similar
// to the way we arrange the cards in a hand. We take one element at a time,
// starts compare from one end and them place them in between the card
// lesser and greater than it.

    public int[] InsertionSort(int[] data){
        int len = data.length;
        int key = 0;
        int i = 0;
        for(int j = 1;j<len;j++){
            key = data[j];
            i = j-1;
            while(i>=0 && data[i]>key){
                data[i+1] = data[i];
                i = i-1;
                data[i+1]=key;
            }
        }
        return data;
    }


//Quicksort
//Quick sort is an algorithm with O(n.log(n)) average timing complexity.
// This algorithm is a recursive algorithm. In here it select an element
// (randomly or middle element of the array) and put elements to left to
// it if its lesser that it or to right side otherwise till all elements
// are sorted.
    public int[] quickSort(int[] data){
        int lenD = data.length;
        int pivot = 0;
        int ind = lenD/2;
        int i,j = 0,k = 0;
        if(lenD<2){
            return data;
        }
        else{
            int[] L = new int[lenD];
            int[] R = new int[lenD];
            int[] sorted = new int[lenD];
            pivot = data[ind];
            for(i=0;i<lenD;i++){
                if(i!=ind){
                    if(data[i]<pivot){
                        L[j] = data[i];
                        j++;
                    }
                    else{
                        R[k] = data[i];
                        k++;
                    }
                }
            }
            int[] sortedL = new int[j];
            int[] sortedR = new int[k];
            System.arraycopy(L, 0, sortedL, 0, j);
            System.arraycopy(R, 0, sortedR, 0, k);
            sortedL = quickSort(sortedL);
            sortedR = quickSort(sortedR);
            System.arraycopy(sortedL, 0, sorted, 0, j);
            sorted[j] = pivot;
            System.arraycopy(sortedR, 0, sorted, j+1, k);
            return sorted;
        }
    }


//
//Merge sort is an algorithm with O(n.log(n)) timing complexity for all cases.
// This algorithm is a divide and conquer algorithm. Merge sort has two parts
// which comparison and merging part.
    public int[] mergeSort(int[] data){
        int lenD = data.length;
        if(lenD<=1){
            return data;
        }
        else{
            int[] sorted = new int[lenD];
            int middle = lenD/2;
            int rem = lenD-middle;
            int[] L = new int[middle];
            int[] R = new int[rem];
            System.arraycopy(data, 0, L, 0, middle);
            System.arraycopy(data, middle, R, 0, rem);
            L = this.mergeSort(L);
            R = this.mergeSort(R);
            sorted = merge(L, R);
            return sorted;
        }
    }

    public int[] merge(int[] L, int[] R){
        int lenL = L.length;
        int lenR = R.length;
        int[] merged = new int[lenL+lenR];
        int i = 0;
        int j = 0;
        while(i<lenL||j<lenR){
            if(i<lenL & j<lenR){
                if(L[i]<=R[j]){
                    merged[i+j] = L[i];
                    i++;
                }
                else{
                    merged[i+j] = R[j];
                    j++;
                }
            }
            else if(i<lenL){
                merged[i+j] = L[i];
                i++;
            }
            else if(j<lenR){
                merged[i+j] = R[j];
                j++;
            }
        }
        return merged;
    }


}
