package com.team.service;

import com.team.entity.Product;

public interface ProductService {
	//Collection<Product> getAllProducts();
	//boolean editProductById(int id);
	boolean insertNewProduct(Product product);
	boolean deleteProductById(String id);
	Product getProductById(String id);
	// ShoppingCart calculatePrice(Map<String, Integer> price);
}
