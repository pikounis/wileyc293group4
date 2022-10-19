package com.team.service;

import com.team.entity.Product;
import com.team.entity.User;

public interface OrderService {
	
	boolean addProductToOrder(User user, Product product);
}
