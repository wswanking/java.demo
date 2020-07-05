package com.wswanking.demo.algorithm.sort;

public interface Sort {
    public Sort withOrder(OrderEnum orderEnum);
    <T extends Comparable<T>> void sort(T[] arrays);
}
