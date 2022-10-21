package com.team.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team.entity.Product;
import com.team.entity.Types;
import com.team.persistence.ProductDAO;
import com.team.persistence.TypesDao;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductDAO productDao;
	
	@Autowired
	TypesDao typesDao;

	
	@Override
	public boolean saveProduct(Product product) {
		if(product != null && !product.getProductName().equals("")) {
			try {
				productDao.save(product);
			}catch (Exception e) {
				return false;
			}
			return true;
		}
		return false;
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
	
	@Override
	public Collection<Types> getAllTypes() {
		return typesDao.findAll();
	}
	
	@Override
	public boolean saveType(Types type) {
		if(!type.getType().equals("")) {
					try {
			typesDao.save(type);
		}catch (Exception e) {
			return false;
		}
		return true;
		}
		return false;
	}
	
	// This is mainly for getting taxes
	@Override
	public Types getType(String type) {
		return typesDao.findById(type).orElse(null);
	}
	

}
