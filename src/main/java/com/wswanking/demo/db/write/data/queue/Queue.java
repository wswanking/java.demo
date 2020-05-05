package com.wswanking.demo.db.write.data.queue;


import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Queue<T> {
    private BlockingQueue<T> blockingQueue = new LinkedBlockingQueue();

    public void produce(T item){
        blockingQueue.offer(item);
    }

    public T consume() throws InterruptedException {
        return blockingQueue.poll();
    }

    public boolean isEmpty(){
        return blockingQueue.isEmpty();
    }
}
