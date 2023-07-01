package com.example.demotest.kafka;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.kafka.clients.consumer.ConsumerRebalanceListener;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import com.alibaba.fastjson.JSONObject;

public class ConsumerDemo {

	private static ExecutorService threadPool = Executors.newFixedThreadPool(20);
	
	public static void main(String[] args) throws Exception {
		KafkaConsumer<String, String> consumer = createConsumer();
		consumer.subscribe(Arrays.asList("test-topic"));
		
		try {
			while(true) {
				ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100000));
				for(ConsumerRecord<String, String> record : records) {
					JSONObject order = JSONObject.parseObject(record.value()); 
					threadPool.submit(new CreditManageTask(order));
				}
				consumer.commitSync();
			}
		} catch(Exception e) {
			e.printStackTrace();
			consumer.close();
		}
	}
	
	private static KafkaConsumer<String, String> createConsumer() {
		Properties props = new Properties();
		props.put("bootstrap.servers", "192.168.135.3:9092,192.168.135.4:9092,192.168.135.5:9092");
		props.put("group.id", "test-group");
		props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		props.put("heartbeat.interval.ms", 1000); // 这个尽量时间可以短一点
		props.put("session.timeout.ms", 10 * 1000); // 如果说kafka broker在10秒内感知不到一个consumer心跳
		props.put("max.poll.interval.ms", 30 * 1000); // 如果30秒才去执行下一次poll
		// 就会认为那个consumer挂了，此时会触发rebalance
		// 如果说某个consumer挂了，kafka broker感知到了，会触发一个rebalance的操作，就是分配他的分区
		// 给其他的cosumer来消费，其他的consumer如果要感知到rebalance重新分配分区，就需要通过心跳来感知
		// 心跳的间隔一般不要太长，1000，500
		props.put("fetch.max.bytes", 10485760);
		props.put("max.poll.records", 500); // 如果说你的消费的吞吐量特别大，此时可以适当提高一些
		props.put("connection.max.idle.ms", -1); // 不要去回收那个socket连接
		// 开启自动提交，他只会每隔一段时间去提交一次offset
		// 如果你每次要重启一下consumer的话，他一定会把一些数据重新消费一遍
		props.put("enable.auto.commit", "true");
		// 每次自动提交offset的一个时间间隔
		props.put("auto.commit.ineterval.ms", "1000");
		// 每次重启都是从最早的offset开始读取，不是接着上一次
		props.put("auto.offset.reset", "earliest"); 
		

		KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(props);
		
		return consumer;
	}
	
	static class CreditManageTask implements Runnable {
		
		private JSONObject order;
		
		public CreditManageTask(JSONObject order) {
			this.order = order;
		}
		
		@Override
		public void run() {
			System.out.println("对订单进行积分的维护......" + order.toJSONString());    
			// 就可以做一系列的数据库的增删改查的事务操作
		}
		
	}
	
}
