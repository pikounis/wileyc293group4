package com.team.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team.entity.Order;
import com.team.entity.Product;
import com.team.entity.User;
import com.team.persistence.OrderDao;

@Service
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	OrderDao orderDao;

	@Override
	public boolean addProductToOrder(User user, Product product) {
		Order oldOrder = orderDao.findByOrderIdAndUser(user.getLastOrder(), user);
		if (oldOrder != null) {
			System.out.println("Order present");
			if (oldOrder.isOpen()) {
				System.out.println("Order open, add to current");
			} else {
				System.out.println("Order closed, create new one with lastOrder+1");
			}
		} else {
			System.out.println("Order not present, create new with lastOrder");
		}
		return false;
	}

}
