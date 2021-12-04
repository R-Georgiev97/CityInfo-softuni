package com.example.cityinfo.web.admin;


import com.example.cityinfo.service.CategoryFieldService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/admin/category-fields")
public class CategoryFieldController {

    private CategoryFieldService categoryFieldService;

    public CategoryFieldController(CategoryFieldService categoryFieldService) {
        this.categoryFieldService = categoryFieldService;
    }

    @DeleteMapping(value = "/{id}", consumes = "application/json", produces = "application/json")
    public void destroy(@PathVariable Long id, HttpServletResponse response ) {
        categoryFieldService.destroy(id);
         response.setStatus(200);
    }

}
