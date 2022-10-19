package com.team.service;

import com.team.entity.Product;
import com.team.entity.ShoppingBasket;

public interface ShoppingBasketService {
	boolean addProductToBasket(Product product, ShoppingBasket basket);
	boolean removeProductfromBasket(Product product, ShoppingBasket basket);
}

