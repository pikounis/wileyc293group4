package com.team.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
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
@IdClass(OrderItemPk.class)
public class OrderItem {
    
	@Id
	@ManyToOne
	private User user;
    @Id
    private int orderId;
    @OneToOne
    private Product product;
    private int quantity;
}
