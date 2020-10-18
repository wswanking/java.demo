package com.wswanking.demo.algorithm.sort.strategy;

import com.wswanking.demo.algorithm.sort.OrderEnum;
import com.wswanking.demo.algorithm.sort.Sort;

public abstract class BaseSort implements Sort {

    protected OrderEnum orderEnum = OrderEnum.ASC;
    @Override
    public Sort withOrder(OrderEnum orderEnum) {
        this.orderEnum = orderEnum;
        return this;
    }

    protected  <T extends Comparable<T>> boolean isValueMatchCondition(T leftValue, T rightValue){
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
}
