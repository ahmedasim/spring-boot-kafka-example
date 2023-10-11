package com.aa.kafkaproducer.service;

import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import com.aa.kafkaproducer.dto.InventoryItemDto;


@Service
public class ItemEventPublisher {

	@Autowired
    private KafkaTemplate<String,Object> kt;
	
	public void sendEventsToTopic(InventoryItemDto dto) {
        try {
            CompletableFuture<SendResult<String, Object>> future = kt.send("kafka-producer-msg", dto);
            future.whenComplete((result, ex) -> {
                if (ex == null) {
                	System.out.println("Sent message=[" + dto.toString() +"] with offset=[" + result.getRecordMetadata().offset() + "] and partition=[" + result.getRecordMetadata().partition() + "]");
                } else {
                	System.out.println("Unable to send message=[" + dto.toString() + "] due to : " + ex.getMessage());
                }
            });

        } catch (Exception ex) {
            System.out.println("ERROR : "+ ex.getMessage());
        }
    }

	public void sendMessageToTopic(String msg) {
		CompletableFuture<SendResult<String, Object>> future = kt.send("kafka-producer-event", msg);
        future.whenComplete((result,ex)->{
            if (ex == null) {
                System.out.println("Sent message=[" + msg +"] with offset=[" + result.getRecordMetadata().offset() + "] and partition=[" + result.getRecordMetadata().partition() + "]");
            } else {
                System.out.println("Unable to send message=[" + msg + "] due to : " + ex.getMessage());
            }
        });
		
	}

}
