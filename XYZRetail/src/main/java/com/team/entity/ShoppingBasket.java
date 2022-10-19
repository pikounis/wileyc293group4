package com.team.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ShoppingBasket {
	@Id @GeneratedValue
	private int shoppingBasketId;
	@OneToMany(mappedBy="shoppingBasket")
	private List<ShoppingBasketProduct> shoppingBasketProducts;
	
}