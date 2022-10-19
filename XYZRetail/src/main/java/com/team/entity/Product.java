package com.team.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Product {
	@Id @GeneratedValue
	private int productId; //1
	private String productName; //2
	private double productPrice; //3
	private int productQuantity; //4
	private String productType; //5
	@OneToMany(mappedBy = "product")
	private List<OrderProduct> productOrder;
	@OneToMany(mappedBy = "product")
	private List<ShoppingBasketProduct> shoppingBasketProducts;
}
