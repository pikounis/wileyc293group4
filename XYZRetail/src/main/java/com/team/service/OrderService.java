package com.team.service;

import java.util.Collection;

import com.team.entity.OrderItem;
import com.team.entity.Product;
import com.team.entity.ShoppingBasketItem;
import com.team.entity.User;
import com.team.exceptions.OutOfStockException;

public interface OrderService {
	
	boolean addProductToOrder(OrderItem item);
	boolean purchaseBasket(User user, Collection<ShoppingBasketItem> items) throws OutOfStockException;
}
