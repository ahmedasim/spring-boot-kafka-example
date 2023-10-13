package com.aa.kafkaconsumer.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaMessageListener {
	
	Logger log = LoggerFactory.getLogger(KafkaMessageListener.class);
	
	@KafkaListener(topics = "kafka-producer-msg", groupId = "aa-group-msg")
	public void consume(String msg) {
		log.info("Received Message: " + msg);
	}

}
