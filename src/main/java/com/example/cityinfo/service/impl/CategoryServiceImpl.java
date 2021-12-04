package com.example.cityinfo.service.impl;


import com.example.cityinfo.model.binding.CategoryBindingModel;
import com.example.cityinfo.model.binding.CategoryFieldBindingModel;
import com.example.cityinfo.model.dto.CategorySeedDto;
import com.example.cityinfo.model.entity.Category;

import com.example.cityinfo.model.entity.CategoryField;
import com.example.cityinfo.model.view.CategoryNameAndSlugView;
import com.example.cityinfo.repository.CategoryFieldRepository;
import com.example.cityinfo.repository.CategoryRepository;
import com.example.cityinfo.service.CategoryFieldService;
import com.example.cityinfo.service.CategoryService;
import com.example.cityinfo.util.ValidationUtil;
import com.google.gson.Gson;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;


@Service
public class CategoryServiceImpl implements CategoryService {

    private static final String CATEGORIES_FILE_NAME = "src/main/resources/files/categories.json";

    private final ModelMapper modelMapper;
    private final CategoryRepository categoryRepository;
    private final CategoryFieldRepository categoryFieldRepository;
    private final CategoryFieldService categoryFieldService;
    private final Gson gson;
    private final ValidationUtil validationUtil;
    private Map<String, String> defaultFields;

    public CategoryServiceImpl(ModelMapper modelMapper,
                               CategoryRepository categoryRepository,
                               CategoryFieldRepository categoryFieldRepository,
                               CategoryFieldService categoryFieldService,
                               Gson gson,
                               ValidationUtil validationUtil) {
        this.modelMapper = modelMapper;
        this.categoryRepository = categoryRepository;
        this.categoryFieldRepository = categoryFieldRepository;
        this.categoryFieldService = categoryFieldService;
        this.gson = gson;
        this.validationUtil = validationUtil;

    }

    /**
     * Seed defaults categories with default category fields
     * They can be edited in admin panel
     *
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
     */
    public void addDefaultFields(Category category) {
        if (this.defaultFields == null) {
            this.defaultFields = new HashMap<>();
            this.defaultFields.put("working_time", "Работно време");
            this.defaultFields.put("location", "Локация");
            this.defaultFields.put("description", "Описание");
            this.defaultFields.put("phone_number", "Телефон за връзка");
            this.defaultFields.put("website", "Уебсайт");
        }
        this.defaultFields.forEach((slug, name) -> {
            categoryFieldService.store(name, slug, category);
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
                .map(category -> modelMapper.map(category, CategoryNameAndSlugView.class))
                .collect(Collectors.toList());
    }

    @Override
    public Category store(CategoryBindingModel categoryBindingModel) {
        Category category = modelMapper.map(categoryBindingModel, Category.class);
        categoryRepository.save(category);
        categoryFieldService.sync(categoryBindingModel.getFieldNames(), categoryBindingModel.getFieldSlugs(), category);

        return category;
    }

    @Override
    @Transactional
    public Category update(CategoryBindingModel categoryBindingModel) throws Exception {
        Category category = categoryRepository.findById(categoryBindingModel.getId())
                .orElseThrow(() -> new Exception("Category not found"));

        category.setName(categoryBindingModel.getName());
        category.setSlug(categoryBindingModel.getSlug());
        category.setDescription(categoryBindingModel.getDescription());

        categoryFieldService.sync(categoryBindingModel.getFieldNames(), categoryBindingModel.getFieldSlugs(), category);
        categoryRepository.save(category);
        return category;
    }

    @Override
    @Transactional
    public void delete(Long id) throws Exception {
        Category category = categoryRepository.getById(id);
        if (category.getObjects().size() > 0) {
            throw new Exception("Тази категория вече се използва и не може да бъде изтрита");
        }
        category.setDeletedAt(LocalDateTime.now());
        categoryRepository.save(category);
    }


    @Override
    @Transactional
    public CategoryBindingModel getCategoryBindingModel(Long id) {
        Category category = categoryRepository.getById(id);
        List<CategoryField> categoryFields = category.getCategoryFields();
        CategoryBindingModel categoryBindingModel = modelMapper.map(category, CategoryBindingModel.class);
        List<CategoryFieldBindingModel> categoryFieldBindingModels = categoryFields
                .stream()
                .map(categoryField -> modelMapper.map(categoryField, CategoryFieldBindingModel.class))
                .collect(Collectors.toList());

        categoryBindingModel.setCategoryFields(categoryFieldBindingModels);
        return categoryBindingModel;
    }
}
