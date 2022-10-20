package com.team.service;

import java.util.Collection;

import com.team.entity.Product;
import com.team.entity.Types;

public interface ProductService {
	//Collection<Product> getAllProducts();
	//boolean editProductById(int id);
	//boolean insertNewProduct(Product product);
	boolean deleteProductById(String id);
	Product getProductById(String id);
	boolean saveProduct(Product product);
	boolean saveType(Types type);
	Collection<Types> getAllTypes();
	Types getType(String type);
}
