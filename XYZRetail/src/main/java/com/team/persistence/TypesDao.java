package com.team.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.team.entity.Types;

@Repository
public interface TypesDao extends JpaRepository<Types, String> {

}
