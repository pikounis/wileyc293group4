package com.team.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="`Order`")
@IdClass(OrderId.class)
public class Order {
	@Id
	private int orderId;
	@ManyToOne
	@Id
	private User user;
	
	private boolean open;
	@ManyToMany
	private List<Product> products;
	
}
