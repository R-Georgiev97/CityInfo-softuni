package com.example.cityinfo.service;


import com.example.cityinfo.model.binding.CategoryBindingModel;
import com.example.cityinfo.model.binding.CategoryFieldBindingModel;
import com.example.cityinfo.model.entity.Category;
import com.example.cityinfo.model.view.CategoryNameAndSlugView;

import java.io.IOException;
import java.util.List;

public interface CategoryService {
    void seedCategoryWithFields() throws IOException;

    List<CategoryNameAndSlugView> getAllCategories();

    Category store(CategoryBindingModel categoryStoreBindingModel);

    void delete(Long id) throws Exception;

    CategoryBindingModel getCategoryBindingModel(Long id);

    Category update(CategoryBindingModel categoryStoreBindingModel) throws Exception;

    List<CategoryFieldBindingModel> getFieldsByCategorySlug(String slug);

    Category getBySlug(String slug);
}
