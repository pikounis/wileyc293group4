package com.team.service;

import java.util.Collection;

import com.team.entity.Product;

public interface ProductService {
	Collection<Product> getAllProducts();
	boolean editProductById(int id);
	boolean insertNewProduct(Product product);
	boolean deleteProductById(int id);
	Product getProductById(int id);
	// ShoppingCart calculatePrice(Map<String, Integer> price);
}
