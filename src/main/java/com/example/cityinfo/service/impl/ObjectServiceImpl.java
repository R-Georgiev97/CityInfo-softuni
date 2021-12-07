package com.example.cityinfo.service.impl;

import com.example.cityinfo.model.binding.ObjectBindingModel;
import com.example.cityinfo.model.entity.Object;
import com.example.cityinfo.model.entity.ObjectData;
import com.example.cityinfo.repository.ObjectRepository;
import com.example.cityinfo.service.*;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
public class ObjectServiceImpl implements ObjectService {

    private static final List<String> OBJECT_FIELDS = new ArrayList<>(Arrays.asList("name", "description", "status", "cityId", "categorySlug","_csrf"));

    private final ObjectRepository objectRepository;
    private final ModelMapper modelMapper;
    private final CityService cityService;
    private final CategoryService categoryService;
    private final UserService userService;
    private final ObjectDataService objectDataService;

    public ObjectServiceImpl(ObjectRepository objectRepository, ModelMapper modelMapper, CityService cityService, CategoryService categoryService, UserService userService, ObjectDataService objectDataService) {
        this.objectRepository = objectRepository;
        this.modelMapper = modelMapper;
        this.cityService = cityService;
        this.categoryService = categoryService;
        this.userService = userService;
        this.objectDataService = objectDataService;
    }

    public void store(ObjectBindingModel objectBindingModel, Map<String,String> requestParams){
        String username = "";
        java.lang.Object principal = SecurityContextHolder. getContext(). getAuthentication(). getPrincipal();
        if (principal instanceof UserDetails){
          username =  ((UserDetails) principal).getUsername();
        }
        Object object = modelMapper.map(objectBindingModel, Object.class);
        object.setApproved(false);
        object.setAverageRating(0.0);
        object.setCity(cityService.getById(objectBindingModel.getCityId()));
        object.setCategory(categoryService.getBySlug(objectBindingModel.getCategorySlug()));
        object.setUser(userService.getByUsername(username));
        objectRepository.save(object);

        storeObjectData(requestParams,object);

    }

    private void storeObjectData(Map<String,String> requestParams,Object object){
        for (var entry : requestParams.entrySet()) {
            if (!OBJECT_FIELDS.contains(entry.getKey())){
                objectDataService.store(entry.getKey(), entry.getValue(), object);
            }
        }
    }
}
