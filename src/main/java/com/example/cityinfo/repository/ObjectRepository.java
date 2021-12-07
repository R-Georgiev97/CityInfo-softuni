package com.example.cityinfo.repository;

import com.example.cityinfo.model.entity.Object;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ObjectRepository extends JpaRepository<Object,Long> {
}
