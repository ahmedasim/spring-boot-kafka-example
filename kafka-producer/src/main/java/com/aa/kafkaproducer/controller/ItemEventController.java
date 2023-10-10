package com.aa.kafkaproducer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aa.kafkaproducer.dto.InventoryItemDto;
import com.aa.kafkaproducer.service.ItemEventPublisher;

@RestController
@RequestMapping("/producer")
public class ItemEventController {

	@Autowired
	private ItemEventPublisher publisher;
	
	@PostMapping("/publish/{msg}")
    public ResponseEntity<?> publishMessage(@PathVariable String msg) {
        try {
            for (int i = 0; i <= 10; i++) {
                publisher.sendMessageToTopic(msg + " -> " + i);
            }
            return ResponseEntity.ok("10 messages published to kafka successfully ..");
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .build();
        }
    }
	
	@PostMapping("/publish")
    public ResponseEntity<?> sendEvents(@RequestBody InventoryItemDto dto) {
		 try {
			 publisher.sendEventsToTopic(dto);
			 return ResponseEntity.ok("Entity message published to kafka successfully ..");
		 } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .build();
		 }
    }

}
