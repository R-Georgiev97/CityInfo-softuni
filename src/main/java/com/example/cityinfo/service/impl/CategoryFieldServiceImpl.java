package com.example.cityinfo.service.impl;

import com.example.cityinfo.model.binding.CategoryFieldBindingModel;
import com.example.cityinfo.model.entity.Category;
import com.example.cityinfo.model.entity.CategoryField;
import com.example.cityinfo.repository.CategoryFieldRepository;
import com.example.cityinfo.service.CategoryFieldService;
import com.example.cityinfo.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.persistence.PreRemove;
import java.security.InvalidParameterException;
import java.util.List;

@Service
public class CategoryFieldServiceImpl implements CategoryFieldService {

    private final CategoryFieldRepository categoryFieldRepository;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;

    public CategoryFieldServiceImpl(CategoryFieldRepository categoryFieldRepository, ValidationUtil validationUtil, ModelMapper modelMapper) {
        this.categoryFieldRepository = categoryFieldRepository;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
    }

    @Override
    public void store(String name, String slug, Category category) {
        CategoryFieldBindingModel categoryFieldBindingModel = new CategoryFieldBindingModel();
        categoryFieldBindingModel.setName(name);
        categoryFieldBindingModel.setSlug(slug);
        if (validationUtil.isValid(categoryFieldBindingModel)) {
            CategoryField categoryField = modelMapper.map(categoryFieldBindingModel, CategoryField.class);
            categoryField.setCategory(category);
            categoryFieldRepository.save(categoryField);
            return;
        }
        throw new InvalidParameterException("Invalid parameters for category field");
    }

    @Override
    public void storeMultiple(String[] fieldNames, String[] fieldSlugs, Category category) {
        if (fieldNames.length == fieldSlugs.length) {
            for (int i = 0; i < fieldNames.length; i++) {
                String name = fieldNames[i];
                String slug = fieldSlugs[i];
                if (this.isSlugFree(slug, category)) {
                    this.store(name, slug, category);
                }
            }
        }
    }

    @Override
    public boolean isSlugFree(String slug, Category category) {
        return categoryFieldRepository.findBySlugAndCategory(slug, category).isEmpty();
    }

    @Override
    public String destroy(Long id) {
        categoryFieldRepository.deleteById(id);
        return null;
    }
}
