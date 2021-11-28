package com.example.cityinfo.service.impl;


import com.example.cityinfo.model.dto.CategorySeedDto;
import com.example.cityinfo.model.entity.Category;

import com.example.cityinfo.model.entity.CategoryField;
import com.example.cityinfo.model.view.CategoryNameAndSlugView;
import com.example.cityinfo.repository.CategoryFieldRepository;
import com.example.cityinfo.repository.CategoryRepository;
import com.example.cityinfo.service.CategoryService;
import com.example.cityinfo.util.ValidationUtil;
import com.google.gson.Gson;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.validation.constraints.Null;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;


@Service
public class CategoryServiceImpl implements CategoryService {

    private static final String CATEGORIES_FILE_NAME = "src/main/resources/files/categories.json";

    private final ModelMapper modelMapper;
    private final CategoryRepository categoryRepository;
    private final CategoryFieldRepository categoryFieldRepository;
    private final Gson gson;
    private final ValidationUtil validationUtil;
    private Map<String,String> defaultFields;

    public CategoryServiceImpl(ModelMapper modelMapper, CategoryRepository categoryRepository, CategoryFieldRepository categoryFieldRepository, Gson gson, ValidationUtil validationUtil) {
        this.modelMapper = modelMapper;
        this.categoryRepository = categoryRepository;
        this.categoryFieldRepository = categoryFieldRepository;
        this.gson = gson;
        this.validationUtil = validationUtil;
    }

    /**
     * Seed defaults categories with default category fields
     * They can be edited in admin panel
     * @throws IOException
     */
    @Override
    public void seedCategoryWithFields() throws IOException {
        if (categoryRepository.count() > 0) {
            return;
        }

        Arrays.stream(gson.fromJson(
                        Files.readString(Path.of(CATEGORIES_FILE_NAME)),
                        CategorySeedDto[].class))
                .filter(validationUtil::isValid)
                .map(categorySeedDto -> modelMapper.map(categorySeedDto, Category.class))
                .forEach(category -> {
                    Category categoryEntity = categoryRepository.save(category);
                    addDefaultFields(categoryEntity);
                });

    }

    /**
     * Set default fields to the category
     * @param category
     */
    public void addDefaultFields(Category category){
        if (this.defaultFields == null){
            this.defaultFields = new HashMap<>();
            this.defaultFields.put("working_time","Работно време");
            this.defaultFields.put("location","Локация");
            this.defaultFields.put("description","Описание");
            this.defaultFields.put("phone_number","Телефон за връзка");
            this.defaultFields.put("website","Уебсайт");
        }
        this.defaultFields.forEach((slug, name) -> {
            CategoryField categoryField = new CategoryField();
            categoryField.setName(name);
            categoryField.setSlug(slug);
            categoryField.setCategory(category);
            categoryFieldRepository.save(categoryField);
        });
    }

    /**
     * Get all categories
     * Returns only name and slug
     */
    @Override
    public List<CategoryNameAndSlugView> getAllCategories() {
        return categoryRepository
                .findAll()
                .stream()
                .map(category -> modelMapper.map(category,CategoryNameAndSlugView.class))
                .collect(Collectors.toList());
    }
}
