package com.learn.bases.arraystest;

/**
 * Created by IntelliJ IDEA.
 * User: bilyk
 * Date: 24.05.12
 * Time: 19:41
 * To change this template use File | Settings | File Templates.
 */
public class SortingAlgorithms {

    public static void main(String[] args) {
        SortingAlgorithms sortingAlgorithms = new SortingAlgorithms();
        int a[] = {0,4,22,7,9,1};
        sortingAlgorithms.printSortedArray(a);
    }


    void printSortedArray(int a[]){  
    for (int i = a.length - 1; i > 0; i--) {
        

            /* В переменной sorted мы будем хранить признак того,
            * отсортирован ли массив. Перед каждым проходом внутреннего
            * цкла будем предполагать, что отсортирован, но если совершим
            * хоть одну перестановку, то значит ещё не конца отсортирован.
            * Этот приём, упрощающий сортировку, называется критерием Айверсона.
            */
            boolean sorted = true;
            /* Во внутреннем цикле мы проходимся по фрагменту массива, который
            * определяется внешним циклом. В этом фрагменте мы устанавливаем
            * правильный порядок между соседними элементами, так попарно
            * обрабатывая весь фрагмент.
            */
            for (int j = 0; j < i; j++) {
                /* Если порядок соседних элементов не правильный, то их
                * надо обменять местами. И запомнить, что была перестановка.
                */
                if (a[j] > a[j+1]) {
                    int temp = a[j];
                    a[j] = a[j+1];
                    a[j+1] = temp;
                    sorted = false;
                }
            }
            /* Если массив отсортирован (т.е. не было ни одной перестановки
            * во внутреннем цикле, значит можно прекращать работу внешнего
            * цикла.
            */
            if(sorted) {
                break;
            }
        }
        for (int i = 0; i < a.length; i++) {
            System.out.println(a[i]);
        }

    }
}
