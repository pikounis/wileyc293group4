package com.team.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class OrderItem {
    
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int orderRowNo;
	
	@ManyToOne
	private User user;
    private int orderId;
   
    @OneToOne
    private Product product;
    private int quantity;
	
    public OrderItem(User user, int orderId, Product product, int quantity) {
		super();
		this.user = user;
		this.orderId = orderId;
		this.product = product;
		this.quantity = quantity;
	} 
    
}
