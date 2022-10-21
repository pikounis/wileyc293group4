package com.team.persistence;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.team.entity.OrderItem;
import com.team.entity.User;

@Repository
public interface OrderDAO extends JpaRepository<OrderItem, Integer>{
	
	public Collection<OrderItem> getByUserAndOrderId(User user, int orderId);
	
}
