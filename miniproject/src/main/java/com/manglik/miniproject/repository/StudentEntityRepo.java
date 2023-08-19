package com.manglik.miniproject.repository;

import com.manglik.miniproject.entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentEntityRepo extends JpaRepository<StudentEntity, Integer> {
}
