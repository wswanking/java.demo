package com.wswanking.demo.algorithm.sort.strategy;

import com.wswanking.demo.algorithm.sort.OrderEnum;

import java.util.Arrays;

/**
 * (非原址排序方式)
 * 时间复杂度O(n) = nlgn
 * 空间复杂度O(n) = n
 */
public class MergeSort extends BaseSort {
    private Integer smallestSize = 2;
    private OrderEnum orderEnum = OrderEnum.ASC;

    @Override
    public <T extends Comparable<T>> void sort(T[] array) {
        if(isLessThanSmallestSize(array.length)){
            return;
        }
        mergeSort(array,0,array.length-1);
    }

    private boolean isLessThanSmallestSize(Integer size){
        return size < smallestSize;
    }

    private <T extends Comparable<T>> void mergeSort (T[]array,Integer startIndex,Integer endIndex){
        Integer size = (endIndex - startIndex)/2;
        Integer midIndex = startIndex + size;
        if(startIndex < endIndex){
            mergeSort(array,startIndex,midIndex);
            mergeSort(array,midIndex+1,endIndex);
            merge(array,startIndex,endIndex);
        }
    }

    private <T extends Comparable<T>>  void merge(T[] array,Integer startIndex,Integer endIndex){
        Integer size = (endIndex - startIndex)/2;
        Integer midIndex = startIndex + size;

        T[]leftArray = Arrays.copyOfRange(array,startIndex,midIndex+1);
        T[]rightArray = Arrays.copyOfRange(array,midIndex+1,endIndex+1);
        Integer leftArrayIndex = 0;
        Integer rightArrayIndex = 0;

        while(startIndex <= endIndex){
            if(isArrayAllMerged(rightArray,rightArrayIndex)){
                array[startIndex] = leftArray[leftArrayIndex];
                leftArrayIndex++;
            }else if (isArrayAllMerged(leftArray,leftArrayIndex)){
                array[startIndex] = rightArray[rightArrayIndex];
                rightArrayIndex++;
            }else if(isValueMatchCondition(leftArray[leftArrayIndex],rightArray[rightArrayIndex])){
                array[startIndex] = leftArray[leftArrayIndex];
                leftArrayIndex++;
            }else if(!isValueMatchCondition(leftArray[leftArrayIndex],rightArray[rightArrayIndex])){
                array[startIndex] = rightArray[rightArrayIndex];
                rightArrayIndex++;
            }
            startIndex++;
        }
    }

    private <T extends Comparable<T>> boolean isArrayAllMerged(T[]array,Integer index){
        return index >= array.length;
    }
}
