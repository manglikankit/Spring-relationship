package com.manglik.miniproject.repository;

import com.manglik.miniproject.entity.AutherEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AutherEntityRepo extends JpaRepository<AutherEntity, Integer> {

}
