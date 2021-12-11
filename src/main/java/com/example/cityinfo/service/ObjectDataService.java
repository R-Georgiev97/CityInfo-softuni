package com.example.cityinfo.service;

import com.example.cityinfo.model.entity.Object;

public interface ObjectDataService {
    void store(String key, String value, Object object);
    void update(String key, String value, Object object);
}
