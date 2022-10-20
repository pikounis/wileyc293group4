package com.team.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team.entity.OrderItem;
import com.team.entity.Product;
import com.team.entity.ShoppingBasketItem;
import com.team.entity.User;
import com.team.persistence.OrderDAO;
import com.team.persistence.ShoppingBasketDao;

@Service
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	OrderDAO orderDao;

	@Override
	public boolean addProductToOrder(OrderItem item) {
		int rows = orderDao.insertProduct(item.getOrderId(), item.getQuantity(), item.getUser(), item.getProduct());
		if (rows > 0)
			return true;
		else
			return false;
	}

	@Override
	public boolean purchaseBasket(User user, Collection<ShoppingBasketItem> items) {
		// For each prod in collection make an orderItem call method above, using the user's lastOrder field for OrderId
		// Then do orderId +1
		return false;
	}

	
	
}
