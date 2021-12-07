package com.example.cityinfo.service.impl;

import com.example.cityinfo.model.entity.Object;
import com.example.cityinfo.model.entity.ObjectData;
import com.example.cityinfo.repository.ObjectDataRepository;
import com.example.cityinfo.service.ObjectDataService;
import org.springframework.stereotype.Service;

@Service
public class ObjectDataServiceImpl implements ObjectDataService {

    private final ObjectDataRepository objectDataRepository;

    public ObjectDataServiceImpl(ObjectDataRepository objectDataRepository) {
        this.objectDataRepository = objectDataRepository;
    }

    @Override
    public void store(String key, String value, Object object) {
        ObjectData objectData = new ObjectData();
        objectData.setFieldKey(key);
        objectData.setFieldValue(value);
        objectData.setObject(object);
        objectDataRepository.save(objectData);
    }
}
