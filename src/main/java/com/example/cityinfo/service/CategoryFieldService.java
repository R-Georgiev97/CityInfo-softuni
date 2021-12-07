package com.example.cityinfo.service;

import com.example.cityinfo.model.binding.CategoryFieldBindingModel;
import com.example.cityinfo.model.entity.Category;

import java.util.List;

public interface CategoryFieldService {

    void store(String name, String slug, Category category);

    void storeMultiple(String[] fieldNames, String[] fieldSlugs, Category category);

    boolean isSlugFree(String slug,Category category);

    String destroy(Long id);

}
