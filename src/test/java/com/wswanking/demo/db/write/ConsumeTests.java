package com.wswanking.demo.db.write;

import com.wswanking.demo.db.write.data.domain.Message;
import com.wswanking.demo.db.write.data.queue.Queue;
import com.wswanking.demo.db.write.data.service.MessageService;
import com.wswanking.demo.db.write.test.ConsumerThreadPool;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@SpringBootTest
class ConsumeTests {
	@Autowired
	private MessageService messageService;
	@Test
	void consumeTest() throws InterruptedException {
		for(Integer index = 0;index < 10 ;index++){
			batchConsumeTest();
		}
	}

	void batchConsumeTest() throws InterruptedException {
		List<ConsumerThreadPool>consumerThreadPools = new ArrayList<>();
		for(Integer poolSize = 1;poolSize <= 32;poolSize++){
			ConsumerThreadPool consumerThreadPool = new ConsumerThreadPool(poolSize,buildQueue(1000L),messageService);
			consume(consumerThreadPool);
			consumerThreadPools.add(consumerThreadPool);
		}

		System.out.println("batch consume test result ");
		for(ConsumerThreadPool consumerThreadPool:consumerThreadPools){
			consumerThreadPool.log();
		}
	}

	void consume(ConsumerThreadPool consumerThreadPool) throws InterruptedException {
		consumerThreadPool.consume();
		while (false == consumerThreadPool.isFinished()){
			//让线程池资源释放并且回收
			Thread.sleep(10000L);
		}
	}

	private Queue buildQueue(Long queueSize){
		Queue queue = new Queue();
		for(Long index = 0L;index < queueSize;index++){
			queue.produce(new Message().setMessage(UUID.randomUUID().toString()+"-message"));
		}
		return queue;
	}
}
