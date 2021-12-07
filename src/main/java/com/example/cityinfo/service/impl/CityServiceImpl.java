package com.example.cityinfo.service.impl;

import com.example.cityinfo.model.binding.CityBindingModel;
import com.example.cityinfo.model.dto.CitySeedDto;
import com.example.cityinfo.model.entity.City;
import com.example.cityinfo.repository.CityRepository;
import com.example.cityinfo.service.CityService;
import com.example.cityinfo.util.ValidationUtil;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CityServiceImpl implements CityService {

    private static final String CITIES_FILE_NAME = "src/main/resources/files/cities.json";

    private final Gson gson;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;
    private final CityRepository cityRepository;

    public CityServiceImpl(Gson gson, ValidationUtil validationUtil, ModelMapper modelMapper, CityRepository cityRepository) {
        this.gson = gson;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
        this.cityRepository = cityRepository;
    }

    @Override
    public void destroy(Long id) {
        cityRepository.deleteById(id);
    }

    @Override
    public void seedCities() throws IOException {
        if (cityRepository.count() == 0) {
            Arrays.stream(gson.fromJson(
                            Files.readString(Path.of(CITIES_FILE_NAME)),
                            CitySeedDto[].class))
                    .filter(validationUtil::isValid)
                    .map(citySeedDto -> modelMapper.map(citySeedDto, City.class))
                    .forEach(cityRepository::save);
        }
    }

    @Override
    public List<CityBindingModel> getAllCities() {
        return cityRepository.findAll().stream()
                .map(city -> modelMapper.map(city, CityBindingModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public void store(CityBindingModel cityBindingModel) {
        City city = modelMapper.map(cityBindingModel, City.class);
        cityRepository.save(city);
    }

    @Override
    public City getById(Long id) {
        return cityRepository.getById(id);
    }
}
