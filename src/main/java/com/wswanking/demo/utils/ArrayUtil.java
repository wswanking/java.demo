package com.wswanking.demo.utils;


import org.apache.commons.lang3.RandomUtils;

public class ArrayUtil {

    public static <T> String getArrayString(T[] list, int begin, int end) {
        StringBuilder sb = new StringBuilder();
        sb.append("\n");
        for (int i = 0; i < begin; i++) {
            sb.append("\t");
        }
        int count = 0;
        for (int i = begin; i <= end; i++) {
            sb.append(list[i] + "\t");
            if (++count == 10) {
                sb.append("\n");
                count = 0;
            }
        }

        return sb.toString();
    }

    public static Integer[] generateRandomIntegerArray(Integer size,Integer minValue,Integer maxValue){
        Integer[]array = null;

        try {
            array = new Integer[size];
            for(Integer index = 0; index < size;index++){
                array[index] = RandomUtils.nextInt(minValue,maxValue);
            }
        }catch (Exception e){

        }
        return array;
    }
}
