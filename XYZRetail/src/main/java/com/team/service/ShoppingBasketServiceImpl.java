package com.team.service;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team.entity.ShoppingBasketItem;
import com.team.entity.User;
import com.team.exceptions.OutOfStockException;
import com.team.persistence.ShoppingBasketDao;
import com.team.persistence.StockItemDAO;

@Service
public class ShoppingBasketServiceImpl implements ShoppingBasketService {
	
	@Autowired
	private ShoppingBasketDao shoppingBasketDao;
	@Autowired
	private StockItemDAO stockDao;
	
	@Override
	public boolean addProductToBasket(ShoppingBasketItem item) throws OutOfStockException {
		// Also add check for Negative input
		System.out.println(stockDao.getByProduct(item.getProduct()).getQuantity());
		if (item.getQuantity() > stockDao.getByProduct(item.getProduct()).getQuantity()) {
			throw new OutOfStockException("Can't add product to shopping cart because we don't have enough in stock");
		}
		Collection<ShoppingBasketItem> shoppingBasketItems = shoppingBasketDao.findByUser(item.getUser());
		shoppingBasketItems.stream().filter((sbi)->sbi.getProduct()==item.getProduct()).findAny().ifPresentOrElse((sbi)->{
			sbi.setQuantity(sbi.getQuantity()+item.getQuantity());
			shoppingBasketDao.save(sbi);
		}, ()->{
			shoppingBasketDao.save(item);
		});
		return true;
		
	}
	
	@Override
	public Collection<ShoppingBasketItem> getShoppingBasket(User user) {
		return shoppingBasketDao.findByUser(user);
	}

	@Override
	public boolean removeProductfromBasket(int id) {
		try {	
			shoppingBasketDao.deleteById(id);
			// Here we also need to add product back in stock
			return true;
		} catch (Exception e){
			return false;
		}
	}

	@Override
	public boolean emptyBasket(User user) {
		int rows = shoppingBasketDao.deleteByUser(user);
		if (rows > 0) {
			return true;
		} else {
			return false;
		}
	}
}
