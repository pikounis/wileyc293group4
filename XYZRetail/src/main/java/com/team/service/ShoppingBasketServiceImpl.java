package com.team.service;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team.entity.Product;
import com.team.entity.ShoppingBasketItem;
import com.team.entity.User;
import com.team.exceptions.NegativeInputException;
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
	public boolean addProductToBasket(ShoppingBasketItem item) throws OutOfStockException, NegativeInputException {
		
		if (item.getQuantity() <= 0) {
			throw new NegativeInputException("Can't add less than one product");
		}
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
	public boolean removeProductfromBasket(User user, Product product) {
		try {	
			int rows = shoppingBasketDao.deleteByUserAndProduct(user, product);
			if (rows>0)
				return true;
			else
				return false;
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
