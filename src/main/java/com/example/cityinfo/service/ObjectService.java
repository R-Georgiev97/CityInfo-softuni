package com.example.cityinfo.service;

import com.example.cityinfo.model.binding.ObjectBindingModel;

import java.util.Map;

public interface ObjectService {

    void store(ObjectBindingModel objectBindingModel, Map<String,String> requestParams);
}
