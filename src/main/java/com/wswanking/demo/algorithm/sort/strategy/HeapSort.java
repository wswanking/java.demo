package com.wswanking.demo.algorithm.sort.strategy;

import com.wswanking.demo.utils.SwapUtils;

/**
 * 堆排序(原址排序)
 * 时间复杂度 O(n) = nlgn
 * 空间复杂度
 */
public class HeapSort extends BaseSort {

    @Override
    public <T extends Comparable<T>> void sort(T[] array) {
        //建立最大堆或者最小堆的时间复杂度为O(n)
        makeHeaps(array,array.length);
        //n次循环

        for( Integer length = array.length-1;length > 0;length--){
            swap(array,0,length);
            //lgn swap
            makeHeap(array,0,length);
        }
    }

    private <T extends Comparable<T>> void makeHeaps(T[] array,Integer length) {
        for(Integer i =length/2;i>=0;i--){
            makeHeap(array,i,length);
        }
    }

    private <T extends Comparable<T>> void makeHeap(T[]array, int index,int length){
        if(isInValidIndex(array, index)){
            return;
        }
        int leftIndex = getLeftIndex(index);
        int rightIndex = getRightIndex(index);
        int maxValueIndex = index;
        if(leftIndex < length && isValueMatchCondition(array[maxValueIndex],array[leftIndex])){
            maxValueIndex = leftIndex;
        }
        if(rightIndex < length && isValueMatchCondition(array[maxValueIndex],array[rightIndex])){
            maxValueIndex = rightIndex;
        }
        if(maxValueIndex != index){
            swap(array, index, maxValueIndex);
            makeHeap(array,maxValueIndex,length);
        }
    }

    private <T extends Comparable<T>> void swap(T[] array, int index, int maxValueIndex) {
        SwapUtils.swap(array,index,maxValueIndex);
    }

    private <T extends Comparable<T>> boolean isInValidIndex(T[] array, int index) {
        return index < 0 || index > array.length ;
    }


    private int getLeftIndex(int index){
        if(0 == index){
            return 1;
        }
        return 2*index;
    }

    private int getRightIndex(int index){
        if(0 == index){
            return 2;
        }
        return 2*index+1;
    }
}
