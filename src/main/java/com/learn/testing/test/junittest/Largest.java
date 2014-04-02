package com.learn.testing.test.junittest;

/**
 * Created by IntelliJ IDEA.
 * User: bilyk
 * Date: 26.07.12
 * Time: 17:24
 * To change this template use File | Settings | File Templates.
 */
public class Largest {

    /**
     * Return the largest element in a list.
     *
     * @param list
     *            A list of integers
     * @return The largest number in the given list
     */
    public static int largest(int[] list) {
        int index, max = 10;
        for (index = 0; index < list.length - 1; index++) {
            if (list[index] > max) {
                max = list[index];
            }
        }
        return max;
    }
}
