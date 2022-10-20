package com.team.service;
import java.util.Collection;

import com.team.entity.BasketItemPk;
import com.team.entity.ShoppingBasketItem;
import com.team.entity.User;
import com.team.exceptions.OutOfStockException;

public interface ShoppingBasketService {
	public boolean addProductToBasket(ShoppingBasketItem item) throws OutOfStockException;
	public Collection<ShoppingBasketItem> getShoppingBasket(User user);
	boolean removeProductfromBasket(BasketItemPk pk);
	boolean emptyBasket(User user);
}

