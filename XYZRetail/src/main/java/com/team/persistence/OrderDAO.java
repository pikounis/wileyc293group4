package com.team.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.team.entity.Order;
import com.team.entity.User;

@Repository
public interface OrderDAO extends JpaRepository<Order, Integer>{
	
	public Order findByOrderIdAndUser(int orderId, User user);
}
