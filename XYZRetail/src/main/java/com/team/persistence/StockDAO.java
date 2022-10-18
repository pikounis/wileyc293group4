package com.team.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import com.team.entity.Stock;

public interface StockDAO extends JpaRepository<Stock, Integer>{

}
