package com.team.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team.entity.Product;
import com.team.entity.User;
import com.team.persistence.OrderDAO;

@Service
public class OrderServiceImpl implements OrderService {

	@Override
	public boolean addProductToOrder(User user, Product product) {
		// TODO Auto-generated method stub
		return false;
	}
	/*
	@Autowired
	OrderDAO orderDao;

	@Override
	public boolean addProductToOrder(User user, Product product) {
//		 Check entered product quantity
//		 if less than stock take them away from stock and add to order
//		 else throw an exception
//		 productDao - update Product for changing the quantity, 
//		 select * from Product where id=product.id and quantity>in.quantity to check for quantity
		Order oldOrder = null;// orderDao.findByOrderIdAndUser(user.getLastOrder(), user);
		if (oldOrder != null) {
			System.out.println("Order present");
			if (oldOrder.isOpen()) {
				// Creating a new order with lastOrder+1, user, and product
				System.out.println("Order open, add to current");
				
			} else {
				System.out.println("Order closed, create new one with lastOrder+1");
				//oldOrder.ListofProduct.add(product)
				//update query in dao to save new list of products
			}
		} else {
			System.out.println("Order not present, create new with lastOrder");
			// Creating a new order with lastOrder, user, and product
			// product needs to go into the list
			// insert into Order in dao   -  Order newOrder = new Order(user.getLastOrder(), )
		}
		
		// set close = true in orderDao
		return false;
	}
	*/
}
