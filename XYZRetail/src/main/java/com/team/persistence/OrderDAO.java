package com.team.persistence;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.team.entity.OrderItem;
import com.team.entity.Product;
import com.team.entity.User;

@Repository
public interface OrderDAO extends JpaRepository<OrderItem, Integer>{
	/*
	@Transactional
	@Modifying
	@Query(value = "insert into shoppingBasketItem values(:ord,:qty,:user,:prod)",nativeQuery = true)
	public int insertProduct(
			@Param("ord") int order,
			@Param("qty") double quantity, 
			@Param("user") User user,
			@Param("prod") Product product
	);
	
	*/
}
