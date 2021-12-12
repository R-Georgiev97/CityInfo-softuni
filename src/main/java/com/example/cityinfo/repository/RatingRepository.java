package com.example.cityinfo.repository;

import com.example.cityinfo.model.entity.Object;
import com.example.cityinfo.model.entity.Rating;
import com.example.cityinfo.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RatingRepository extends JpaRepository<Rating,Long> {

    Boolean existsByObjectAndUser(Object object, User user);
}
