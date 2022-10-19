package com.team.entity;

import java.io.Serializable;

import lombok.Data;

@Data
public class OrderItemPk implements Serializable {
	
	 private User user;
	 private int orderId;
}
