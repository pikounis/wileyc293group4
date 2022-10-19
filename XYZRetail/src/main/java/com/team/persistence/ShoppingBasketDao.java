package com.team.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import com.team.entity.ShoppingBasket;

public interface ShoppingBasketDao extends JpaRepository<ShoppingBasket, Integer> {

}
