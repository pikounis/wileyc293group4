package com.team.service;
import java.util.Collection;

import com.team.entity.ShoppingBasketItem;
import com.team.entity.User;

public interface ShoppingBasketService {
	public boolean addProductToBasket(ShoppingBasketItem item);
	public Collection<ShoppingBasketItem> getShoppingBasket(User user);
	//boolean removeProductfromBasket(Product product, ShoppingBasket basket);
}

