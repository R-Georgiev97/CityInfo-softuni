package com.example.cityinfo.web.admin;

import com.example.cityinfo.model.binding.CategoryStoreBindingModel;
import com.example.cityinfo.service.CategoryService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

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
        if (!model.containsAttribute("categoryStoreBindingModel")) {
            model.
                    addAttribute("categoryStoreBindingModel", new CategoryStoreBindingModel());
        }
        return TEMPLATE_DIRECTORY + "create";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("")
    public String store(@Valid CategoryStoreBindingModel categoryStoreBindingModel,
                        BindingResult bindingResult,
                        RedirectAttributes redirectAttributes){
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("categoryStoreBindingModel", categoryStoreBindingModel)
                    .addFlashAttribute("org.springframework.validation.BindingResult.categoryStoreBindingModel", bindingResult);
            return "redirect:/"+ TEMPLATE_DIRECTORY + "create";
        }

        return "redirect:/"+TEMPLATE_DIRECTORY;
    }

}
