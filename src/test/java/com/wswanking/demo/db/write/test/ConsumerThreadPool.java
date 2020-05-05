package com.wswanking.demo.db.write.test;

import com.wswanking.demo.db.write.data.queue.Queue;
import com.wswanking.demo.db.write.data.domain.Message;
import com.wswanking.demo.db.write.data.service.MessageService;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class ConsumerThreadPool {
    private Integer poolSize;
    private Queue<Message> queue;
    private ExecutorService executorService;
    private MessageService messageService;
    private long startTime = System.currentTimeMillis();
    private Long finishedTime = null;
    private Integer finishThreadCount = 0;
    private boolean isFinished = false;


    public ConsumerThreadPool(Integer poolSize, Queue<Message>queue,MessageService messageService){
        this.poolSize = poolSize;
        this.queue = queue;
        this.executorService = Executors.newFixedThreadPool(poolSize);
        this.messageService = messageService;
    }

    public void consume(){
        for(int index = 0;index < poolSize; index++){
            run();
        }
    }

    private void run() {
        this.executorService.execute(new Runnable() {
            @Override
            public void run() {
                while (false == queue.isEmpty()){
                    try {
                        if(queue.isEmpty()){
                            break;
                        }
                        Message message = queue.consume();
                        if(null == message){
                            continue;
                        }
                        messageService.insertMessage(message);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                setFinishedTime(System.currentTimeMillis());
                increaseFinishedCount();
            }
        });
    }

    public void setFinishedTime(Long finishedTime) {
        this.finishedTime = finishedTime;
    }

    private synchronized void increaseFinishedCount(){
        this.finishThreadCount++;
        if(this.finishThreadCount == this.poolSize){
            isFinished = true;
        }
    }

    public boolean isFinished() {
        return isFinished;
    }

    public synchronized void log(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("thread pool size:").append(poolSize).append(" cost ").append(finishedTime-startTime).append("ms");
        System.out.println(stringBuilder.toString());
    }
}
