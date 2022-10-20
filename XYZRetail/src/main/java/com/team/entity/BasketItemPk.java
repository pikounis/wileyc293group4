package com.team.entity;

import java.io.Serializable;

import lombok.Data;

@Data
public class BasketItemPk implements Serializable {
	
	 private User user;
	 private Product product;
}
