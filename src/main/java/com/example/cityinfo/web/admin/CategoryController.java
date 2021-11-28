package com.example.cityinfo.web.admin;

import com.example.cityinfo.service.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/categories")
public class CategoryController {

    static final String TEMPLATE_DIRECTORY = "admin/categories/";

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("")
    public String index(Model model){
        model.addAttribute("categories",categoryService.getAllCategories());
        return TEMPLATE_DIRECTORY + "index";
    }

    @GetMapping("/create")
    public String create(Model model){
        //TODO
        return TEMPLATE_DIRECTORY + "create";
    }

}
