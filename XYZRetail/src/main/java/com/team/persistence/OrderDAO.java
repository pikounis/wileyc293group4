package com.team.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.team.entity.OrderItem;
import com.team.entity.OrderItemPk;
import com.team.entity.User;

@Repository
public interface OrderDAO extends JpaRepository<OrderItem, OrderItemPk>{
	
	public OrderItem findByOrderIdAndUser(int orderId, User user);
}
