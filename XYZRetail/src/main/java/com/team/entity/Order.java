package com.team.entity;

import java.util.List;

import javax.persistence.Entity;
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
@Table(name="`Order`")
public class Order {
	@Id
	private int orderId;
	@ManyToOne
	private User user;
	
	private boolean open;
	@OneToMany(mappedBy="order")
	private List<OrderProduct> orderProducts;
	
}
