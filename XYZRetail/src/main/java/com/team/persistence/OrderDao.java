package com.team.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import com.team.entity.Order;

public interface OrderDao extends JpaRepository<Order, Integer>{

}
