package com.team.service;

import java.util.Collection;

import com.team.entity.Product;
import com.team.entity.StockItem;

public interface StockItemService {
	
	public Collection<StockItem> getAllStockItems(); 
	public boolean insertNewStockItem(StockItem stock);
	public boolean deleteStockById(String id);
	public StockItem getProductById(Product product);

}
