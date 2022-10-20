package com.team.persistence;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.team.entity.Product;
import com.team.entity.ShoppingBasketItem;
import com.team.entity.User;

@Repository
public interface ShoppingBasketDao extends JpaRepository<ShoppingBasketItem, Integer> {
	/*
	@Transactional
	@Modifying
	@Query(value = "insert into shoppingBasketItem values(:qty,:user,:prod)",nativeQuery = true)
	public int insertProduct(
			@Param("user") User user,
			@Param("qty") double quantity, 
			@Param("prod") Product product
	);
	*/
	public Collection<ShoppingBasketItem> findByUser(User user);
	
	@Transactional
	@Modifying
	public int deleteByUser(User user);
	
	public ShoppingBasketItem findByProduct(Product product);

}
