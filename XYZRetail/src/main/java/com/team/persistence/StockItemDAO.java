package com.team.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.team.entity.Product;
import com.team.entity.StockItem;

@Repository
public interface StockItemDAO extends JpaRepository<StockItem, String> {
	
	public StockItem getByProduct(Product product);
	
}
