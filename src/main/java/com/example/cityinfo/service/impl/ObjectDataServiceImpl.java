package com.example.cityinfo.service.impl;

import com.example.cityinfo.model.entity.Object;
import com.example.cityinfo.model.entity.ObjectData;
import com.example.cityinfo.repository.ObjectDataRepository;
import com.example.cityinfo.service.CategoryFieldService;
import com.example.cityinfo.service.ObjectDataService;
import org.springframework.stereotype.Service;

@Service
public class ObjectDataServiceImpl implements ObjectDataService {

    private final ObjectDataRepository objectDataRepository;
    private final CategoryFieldService categoryFieldService;

    public ObjectDataServiceImpl(ObjectDataRepository objectDataRepository, CategoryFieldService categoryFieldService) {
        this.objectDataRepository = objectDataRepository;
        this.categoryFieldService = categoryFieldService;
    }

    @Override
    public void store(String key, String value, Object object) {
        ObjectData objectData = new ObjectData();
        objectData.setFieldKey(key);
        System.out.println(key + value + "here");
        objectData.setFieldName(categoryFieldService.findCategoryFieldBySlug(key).getName());
        objectData.setFieldValue(value);
        objectData.setObject(object);
        objectDataRepository.save(objectData);
    }

    @Override
    public void update(String key, String value, Object object) {
        ObjectData objectData = objectDataRepository.findByFieldKeyAndObject(key,object);
        objectData.setFieldValue(value);
        objectDataRepository.save(objectData);
    }
}
