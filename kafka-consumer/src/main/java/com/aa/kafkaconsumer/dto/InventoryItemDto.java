package com.aa.kafkaconsumer.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class InventoryItemDto {

	private Long itemId;
	private String itemName;
	private BigDecimal itemCost;
	private BigDecimal itemQty;
}
