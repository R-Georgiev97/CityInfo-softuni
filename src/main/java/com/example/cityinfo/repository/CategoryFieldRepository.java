package com.example.cityinfo.repository;

import com.example.cityinfo.model.entity.Category;
import com.example.cityinfo.model.entity.CategoryField;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryFieldRepository extends JpaRepository<CategoryField, Long> {

    Optional<CategoryField> findBySlugAndCategory(String slug, Category category);
}
