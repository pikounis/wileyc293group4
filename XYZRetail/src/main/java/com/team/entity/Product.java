package com.team.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Product {
	@Id
	private int productId; //1
	private String productName; //2
	private double productPrice; //3
	private int productQuantity; //4
	private String productType; //5
}
