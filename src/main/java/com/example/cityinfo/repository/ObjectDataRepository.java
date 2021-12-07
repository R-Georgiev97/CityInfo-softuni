package com.example.cityinfo.repository;

import com.example.cityinfo.model.entity.ObjectData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ObjectDataRepository extends JpaRepository<ObjectData, Long> {
}
