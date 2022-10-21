package com.team.service;
import java.util.Collection;

import com.team.entity.Product;
import com.team.entity.ShoppingBasketItem;
import com.team.entity.User;
import com.team.exceptions.NegativeInputException;
import com.team.exceptions.OutOfStockException;

public interface ShoppingBasketService {
	public boolean addProductToBasket(ShoppingBasketItem item) throws OutOfStockException, NegativeInputException;
	public Collection<ShoppingBasketItem> getShoppingBasket(User user);
	boolean removeProductfromBasket(User user, Product product);
	boolean emptyBasket(User user);
}

