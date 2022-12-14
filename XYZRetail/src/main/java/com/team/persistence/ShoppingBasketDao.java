package com.team.persistence;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import com.team.entity.Product;
import com.team.entity.ShoppingBasketItem;
import com.team.entity.User;

@Repository
public interface ShoppingBasketDao extends JpaRepository<ShoppingBasketItem, Integer> {
	
	public Collection<ShoppingBasketItem> findByUser(User user);
	
	@Transactional
	@Modifying
	public int deleteByUser(User user);
	
	@Transactional
	@Modifying
	public int deleteByUserAndProduct(User user, Product product);
	
	public ShoppingBasketItem findByProduct(Product product);
	public ShoppingBasketItem findByProductAndUser(Product product, User user);
	
	

}
