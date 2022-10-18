package com.team.service;

import java.util.Collection;
import java.util.Map;

import com.team.entity.Product;
import com.team.entity.ShoppingCart;
import com.team.entity.Stock;

public interface ProductService {
	Collection<Product> getAllProduct();
	boolean editProductType(String type);
	boolean insertStockType(Product product);
	boolean deleteStock(Product product);
	// ShoppingCart calculatePrice(Map<String, Integer> price);
}
