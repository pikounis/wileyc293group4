package com.team.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "LoginUser")
public class User {
	@Id
	private String username;
	private boolean admin;
	private String password;
	private int lastOrder;
	@OneToOne
	private ShoppingBasket shoppingBasket;
}
