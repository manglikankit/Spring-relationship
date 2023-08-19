package com.manglik.miniproject.repository;

import com.manglik.miniproject.entity.CourseEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseEntityRepo extends JpaRepository<CourseEntity, Integer> {
}
