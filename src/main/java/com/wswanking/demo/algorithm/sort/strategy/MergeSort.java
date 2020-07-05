package com.wswanking.demo.algorithm.sort.strategy;

import com.wswanking.demo.algorithm.sort.OrderEnum;
import com.wswanking.demo.algorithm.sort.Sort;

import java.util.Arrays;


public class MergeSort implements Sort {
    private Integer smallestSize = 2;
    private OrderEnum orderEnum = OrderEnum.ASC;

    @Override
    public Sort withOrder(OrderEnum orderEnum) {
        this.orderEnum = orderEnum;
        return this;
    }

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
            }else if(isLeftArrayValueMatchCondition(leftArray[leftArrayIndex],rightArray[rightArrayIndex])){
                array[startIndex] = leftArray[leftArrayIndex];
                leftArrayIndex++;
            }else if(!isLeftArrayValueMatchCondition(leftArray[leftArrayIndex],rightArray[rightArrayIndex])){
                array[startIndex] = rightArray[rightArrayIndex];
                rightArrayIndex++;
            }
            startIndex++;
        }
    }

    private <T extends Comparable<T>> boolean isLeftArrayValueMatchCondition(T leftValue,T rightValue){
        boolean match = false;
        switch (orderEnum){
            case ASC:
                match = leftValue.compareTo(rightValue) <= 0;
                break;
            case DESC:
                match = leftValue.compareTo(rightValue) >= 0;
                break;
            default:
                break;
        }
        return match;
    }

    private <T extends Comparable<T>> boolean isArrayAllMerged(T[]array,Integer index){
        return index >= array.length;
    }
}
