package com.team.persistence;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.team.entity.User;

@Repository
public interface UserDAO extends JpaRepository<User, String>{
	
	public User getUserByUsernameAndPassword(String username, String password);
	
	@Transactional
	@Modifying
	@Query(value = "insert into loginuser values(:username,:admin,:password)",nativeQuery = true)
	public int insertUser(@Param("username") String username, @Param("admin") boolean admin, @Param("password") String password);
	
}
