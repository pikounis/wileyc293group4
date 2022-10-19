package com.team.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team.entity.ShoppingBasketItem;
import com.team.entity.User;
import com.team.persistence.ShoppingBasketDao;

@Service
public class ShoppingBasketServiceImpl implements ShoppingBasketService {
	
	@Autowired
	private ShoppingBasketDao shoppingBasketDao;
	@Override
	public boolean addProductToBasket(ShoppingBasketItem item) {
		int rows = shoppingBasketDao.insertProduct(item.getUser(), item.getQuantity(), item.getProduct());
		if (rows > 0) 
			return true;
		return true;
	}
	
	/*
	@Override
	public boolean removeProductfromBasket(Product product, ShoppingBasket basket) {
		//product needs to become a shoppingBasketProduct
		basket.getShoppingBasketProducts().remove(null);
		try{
			shoppingBasketDao.save(basket);
		}catch (Exception e) {
			System.out.println("failed to remove product to basket");
			return false;
		};
		return true;
	}
	
	*/
	
	@Override
	public Collection<ShoppingBasketItem> getShoppingBasket(User user) {
		return shoppingBasketDao.findByUser(user);
	}
}
