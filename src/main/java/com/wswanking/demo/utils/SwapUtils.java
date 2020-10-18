package com.wswanking.demo.utils;

public class SwapUtils {
    public static void swap(Object[] array, int index, int maxValueIndex) {
        Object tmpValue = array[index];
        if(tmpValue instanceof Integer) {
            Integer maxValue = new Integer((Integer) array[maxValueIndex]);
            Integer value = new Integer((Integer) array[index]);
            array[maxValueIndex] = value;
            array[index] = maxValue;
        }
    }
}
