package com.team.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class StockItem implements Serializable {
	
	@Id
	private String productName;
	@MapsId
	@JoinColumn(name = "productName")
	@OneToOne(cascade = CascadeType.ALL)
	private Product product;
	private int quantity;
}
