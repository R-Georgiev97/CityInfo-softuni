package com.example.cityinfo.web.api;

import com.example.cityinfo.model.binding.CategoryFieldBindingModel;
import com.example.cityinfo.service.CategoryFieldService;
import com.example.cityinfo.service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/api")
public class CategoryFieldApi {

    private final CategoryService categoryService;

    public CategoryFieldApi(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/category-fields")
    public ResponseEntity<List<CategoryFieldBindingModel>> getCategoryFieldsBySlug(@RequestParam("category_slug") String categorySlug){
        List<CategoryFieldBindingModel> fields = categoryService.getFieldsByCategorySlug(categorySlug);
        return ResponseEntity.
                ok(fields);
    }

}
