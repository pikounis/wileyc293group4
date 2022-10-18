package com.team.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team.entity.Product;
import com.team.persistence.ProductDAO;

@Service
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	ProductDAO productDao;
	
	@Override
	public Collection<Product> getAllProducts() {
		return productDao.findAll();
	}

	@Override
	public boolean editProductById(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean insertNewProduct(Product product) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteProductById(int id) {
		// TODO Auto-generated method stub
		return false;
	}

}
