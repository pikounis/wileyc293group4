package com.team.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team.entity.Product;
import com.team.entity.StockItem;
import com.team.persistence.StockItemDAO;

@Service
public class StockItemServiceImpl implements StockItemService {
	
	@Autowired
	StockItemDAO stockDao;
	
	@Override
	public Collection<StockItem> getAllStockItems() {
		return stockDao.findAll();
	}
	
	@Override
	public boolean insertNewStockItem(StockItem stock) {
		try {
			int rows = stockDao.insertProduct(stock.getProduct(), stock.getQuantity());
			if (rows > 0) 
				return true;
			else
				return false;
		} catch (Exception e){
			return false;
		}		
	}
	
	@Override
	public boolean deleteStockById(Product product) {
		try {	
			stockDao.deleteById(product);
			return true;
		} catch (Exception e){
			return true;
		}
	}

	@Override
	public StockItem getProductById(Product product) {
		return stockDao.findById(product).orElse(null);
	}
	
}
