package com.example.cityinfo.repository;

import com.example.cityinfo.model.entity.Object;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ObjectRepository extends JpaRepository<Object,Long> {

    List<Object> findTop5ByApprovedOrderByIdDesc(@Param("approved") Boolean approved);

    List<Object> findAllByApprovedOrderById(@Param("approved") Boolean approved);

    List<Object>findAllByApproved(@Param("approved") Boolean approved);
}
