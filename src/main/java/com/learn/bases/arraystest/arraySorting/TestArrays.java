package com.learn.bases.arraystest.arraySorting;

/**
 * Created by IntelliJ IDEA.
 * User: Flash
 * Date: 25.05.12
 * Time: 21:35
 * To change this template use File | Settings | File Templates.
 */
public class TestArrays {
   int[] arrayVar = new int[]{5,3,2,8,4,19,4};

    public static void main(String[] args) {
        TestArrays3 testArrays3 = new TestArrays3();
        int[] array = testArrays3.arrayVar;
        int size = testArrays3.arrayVar.length;
        int step = size / 2;//инициализируем шаг.
        while (step > 0)//пока шаг не 0
        {
            for (int i = 0; i < (size - step); i++)
            {
                int j = i;
                //будем идти начиная с i-го элемента
                while (j >= 0 && array[j] > array[j + step])
                //пока не пришли к началу массива
                //и пока рассматриваемый элемент больше
                //чем элемент находящийся на расстоянии шага
                {
                    //меняем их местами
                    int temp = array[j];
                    array[j] = array[j + step];
                    array[j + step] = temp;
                    j--;
                }
            }
            step = step / 2;//уменьшаем шаг
        }
        // Выводим отсортированный массив
        for (int i = 0; i < size; i++) {
            System.out.println(array[i] + " ");
        }

    }
}
