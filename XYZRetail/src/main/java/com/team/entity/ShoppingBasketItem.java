package com.team.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class ShoppingBasketItem implements Serializable {
    
	
	//private int productId;
	
	//@JoinColumn(name = "productId")
	@ManyToOne
    private User user;
	@Id
	private String productName;
	@MapsId
	@JoinColumn(name = "productName")
	@OneToOne
	private Product product;
    private int quantity;
}