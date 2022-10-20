package com.team.persistence;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.team.entity.Product;
import com.team.entity.StockItem;

@Repository
public interface StockItemDAO extends JpaRepository<StockItem, String> {
	
	public StockItem getByProduct(Product product);
	/*
	@Transactional
	@Modifying
	@Query(value = "insert into stockItem values(:prod,:qty)",nativeQuery = true)
	public int insertProduct(
			@Param("prod") Product product,
			@Param("qty") double quantity 
	); */
}
