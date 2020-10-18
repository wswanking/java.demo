package com.wswanking.demo.sort;


import com.wswanking.demo.algorithm.sort.Sort;
import com.wswanking.demo.algorithm.sort.SortStrategy;
import com.wswanking.demo.algorithm.sort.strategy.HeapSort;
import com.wswanking.demo.algorithm.sort.strategy.MergeSort;
import com.wswanking.demo.utils.ArrayUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class SortStrategyTest {

    public Integer[] buildRandomIntegerArray(Integer size,Integer minValue,Integer maxValue){
        return ArrayUtil.generateRandomIntegerArray(size,minValue,maxValue);
    }

    @Test
    void mergeSortTest(){
        sortTest(new MergeSort());
    }

    @Test
    void HeadSortTest(){
        sortTest(new HeapSort());
    }

    private void sortTest(Sort sort) {
        Integer[]unSortedArray = buildRandomIntegerArray(10000,0,100000);
        Integer[]sortedArray = Arrays.copyOf(unSortedArray,unSortedArray.length);
        SortStrategy sortStrategy = new SortStrategy(sort);
        sortStrategy.sort(sortedArray);
        Assertions.assertArrayEquals(sortedArray,getExpectedSortedArray(unSortedArray));
    }

    private Integer[] getExpectedSortedArray(Integer[] array){
        Integer[] expectedSortedArray = Arrays.copyOf(array,array.length);
        Arrays.sort(expectedSortedArray);
        return expectedSortedArray;
    }
}
