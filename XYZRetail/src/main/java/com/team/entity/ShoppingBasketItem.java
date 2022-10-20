package com.team.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int shoppingBasketRowNo;
	@ManyToOne
	private User user;
	@OneToOne
	private Product product;
	private int quantity;
	
	public ShoppingBasketItem(User user, Product product, int quantity) {
		super();
		this.user = user;
		this.product = product;
		this.quantity = quantity;
	}
	
}