package com.team.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team.entity.OrderItem;
import com.team.entity.Product;
import com.team.entity.ShoppingBasketItem;
import com.team.entity.StockItem;
import com.team.entity.User;
import com.team.exceptions.OutOfStockException;
import com.team.persistence.OrderDAO;
import com.team.persistence.ShoppingBasketDao;
import com.team.persistence.StockItemDAO;

@Service
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	OrderDAO orderDao;
	
	@Autowired
	StockItemDAO stockDao;

	@Override
	public boolean addProductToOrder(OrderItem item) {
		try {
			orderDao.save(item);
			return true;
		}
		catch (Exception e){
			return false;
		}
	}
	
	//item.getQuantity() > stockDao.getByProduct(item.getProduct()).getQuantity()
	@Override
	public boolean purchaseBasket(User user, Collection<ShoppingBasketItem> items) throws OutOfStockException {
		boolean isOutOfStock = items.stream()
		.anyMatch((item) -> item.getQuantity() > stockDao.getByProduct(item.getProduct()).getQuantity());
		
		if (isOutOfStock) {
			throw new OutOfStockException("Hey out of stock!");
		}
		
		items.stream()
		.map((item) -> {
			StockItem changing = stockDao.getByProduct(item.getProduct());
			changing.setQuantity(changing.getQuantity() - item.getQuantity());
			return stockDao.save(changing);
							});
		
		boolean saveAttempt = items.stream()
		.map(item -> addProductToOrder(new OrderItem(user, user.getOrderNumber(), item.getProduct(), item.getQuantity())))
		.allMatch(t -> t == true);
		
		return saveAttempt;
			
	}


	
}
