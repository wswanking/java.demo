package com.wswanking.demo.algorithm.sort;

import com.wswanking.demo.utils.ArrayUtil;

public class SortStrategy {

    private Long cost = 0L;

    private Sort sort;

    public SortStrategy(Sort sort){
        this.sort = sort;
    }

    public void sort(Integer[] array){
        Long startTimeStamps = System.currentTimeMillis();
        System.out.println("before sorted:"+ ArrayUtil.getArrayString(array,0, getPrintLength(array)));
        sort.sort(array);
        cost = System.currentTimeMillis() - startTimeStamps;
        System.out.println("cost:"+ cost);
        System.out.println("after sorted:"+ArrayUtil.getArrayString(array,0, getPrintLength(array)));
    }

    private int getPrintLength(Integer[] array) {
        Integer printLength = 10;
        return printLength > array.length ? array.length-1:printLength-1;
    }
}
