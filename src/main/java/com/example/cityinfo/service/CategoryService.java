package com.example.cityinfo.service;


import com.example.cityinfo.model.view.CategoryNameAndSlugView;

import java.io.IOException;
import java.util.List;

public interface CategoryService {
    void seedCategoryWithFields() throws IOException;
    List<CategoryNameAndSlugView> getAllCategories();
}
