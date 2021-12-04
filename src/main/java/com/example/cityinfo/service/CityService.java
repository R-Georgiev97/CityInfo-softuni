package com.example.cityinfo.service;

import com.example.cityinfo.model.binding.CityBindingModel;

import java.io.IOException;
import java.util.List;

public interface CityService {

    void destroy(Long id);

    void seedCities() throws IOException;

    List<CityBindingModel> getAllCities();

    void store(CityBindingModel cityBindingModel);
}
