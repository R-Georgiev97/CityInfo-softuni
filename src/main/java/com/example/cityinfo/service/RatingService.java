package com.example.cityinfo.service;

import com.example.cityinfo.model.entity.Object;

public interface RatingService {
    void store(Integer rate, Object object);
    Boolean userAlreadyVotedForObject(Object object);
}
