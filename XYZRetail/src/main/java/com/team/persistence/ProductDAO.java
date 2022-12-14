package com.team.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.team.entity.Product;

@Repository
public interface ProductDAO extends JpaRepository<Product, String>{
	
}
