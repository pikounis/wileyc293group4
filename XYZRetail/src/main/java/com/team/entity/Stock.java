package com.team.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Stock {
	@Id
	private int id;
	private String stockType;
	private double price;
	private double TaxType;
	private int Quantity;
}
