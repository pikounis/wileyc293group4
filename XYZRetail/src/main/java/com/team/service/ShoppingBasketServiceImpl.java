package com.team.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.team.entity.Product;
import com.team.entity.ShoppingBasket;
import com.team.persistence.ShoppingBasketDao;

public class ShoppingBasketServiceImpl implements ShoppingBasketService {
	@Autowired
	private ShoppingBasketDao shoppingBasketDao;
	@Override
	public boolean addProductToBasket(Product product, ShoppingBasket basket) {
		basket.getProducts().add(product);
		try{
			shoppingBasketDao.save(basket);
		}catch (Exception e) {
			System.out.println("failed to add product to basket");
			return false;
		};
		return true;
	}
	@Override
	public boolean removeProductfromBasket(Product product, ShoppingBasket basket) {
		basket.getProducts().remove(product);
		try{
			shoppingBasketDao.save(basket);
		}catch (Exception e) {
			System.out.println("failed to remove product to basket");
			return false;
		};
		return true;
	}
	
	
}
