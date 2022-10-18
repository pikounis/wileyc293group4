package com.team.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import com.team.entity.Product;

public interface ProductDAO extends JpaRepository<Product, Integer>{

}
