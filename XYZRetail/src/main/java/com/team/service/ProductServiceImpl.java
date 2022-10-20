package com.team.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team.entity.Product;
import com.team.entity.Types;
import com.team.persistence.ProductDAO;
import com.team.persistence.TypesDao;

@Service
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	ProductDAO productDao;
	
	@Autowired
	TypesDao typesDao;
	
	/*
	@Override
	public Collection<Product> getAllProducts() {
		return productDao.findAll();
	}

	@Override
	public boolean editProductById(int id) {
		// TODO Auto-generated method stub
		return false;
	}
	*/
	
	@Override
	public boolean insertNewProduct(Product product) {
		Types type = typesDao.findById(product.getProductType().getType()).orElse(null);
		try {
			int rows = productDao.insertProduct(product.getProductName(), product.getProductPrice(), type);
			if (rows > 0) 
				return true;
			else
				return false;
		} catch (Exception e){
			return false;
		}
			
	}

	@Override
	public boolean deleteProductById(String id) {
		productDao.deleteById(id);
		return true;
	}

	@Override
	public Product getProductById(String id) {
		return productDao.findById(id).orElse(null);
	}

}
