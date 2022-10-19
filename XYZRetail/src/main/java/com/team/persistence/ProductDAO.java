package com.team.persistence;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.team.entity.Product;

@Repository
public interface ProductDAO extends JpaRepository<Product, Integer>{
	
	@Transactional
	@Modifying
	@Query(value = "insert into product values(:id,:name,:price,:qty,:type)",nativeQuery = true)
	public int insertProduct(@Param("id") int id,@Param("name") String name,@Param("price") double price, @Param("qty") int quantity,@Param("type") String type);

}
