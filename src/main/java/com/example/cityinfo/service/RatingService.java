package com.example.cityinfo.service;

import com.example.cityinfo.model.entity.Object;
import com.example.cityinfo.model.entity.Rating;

import java.util.List;

public interface RatingService {
    void store(Integer rate, Object object);
    Boolean userAlreadyVotedForObject(Object object);
    List<Rating> getAllByObject(Object object);
}
