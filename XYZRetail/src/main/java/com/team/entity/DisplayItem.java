package com.team.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DisplayItem {
	
	private Product product;
	private int quantity;
	private String type;
	private double price;
	private double taxes;
	private double wholePrice;

}
