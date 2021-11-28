package com.example.cityinfo.repository;

import com.example.cityinfo.model.entity.CategoryField;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryFieldRepository extends JpaRepository<CategoryField, Long> {
}
