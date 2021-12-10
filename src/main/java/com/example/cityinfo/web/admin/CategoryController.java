package com.example.cityinfo.web.admin;

import com.example.cityinfo.model.binding.CategoryBindingModel;
import com.example.cityinfo.model.binding.CategoryFieldBindingModel;

import com.example.cityinfo.service.CategoryService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Set;

@Controller
@PreAuthorize("hasRole('ROLE_ADMIN')")
@RequestMapping("/admin/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("categories", categoryService.getAllCategories());
        return  "admin/categories/index";
    }

    @GetMapping("/create")
    public String create(Model model) {
        if (!model.containsAttribute("categoryBindingModel")) {
            model.
                    addAttribute("categoryBindingModel", new CategoryBindingModel());
        }
        return "admin/categories/create";
    }

    @PostMapping()
    public String store(@Valid CategoryBindingModel categoryBindingModel,
                        @RequestParam(value = "field_name[]",required = false) String[] fieldNames,
                        @RequestParam(value = "field_slug[]",required = false) String[] fieldSlugs,
                        BindingResult bindingResult,
                        RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {

            redirectAttributes.addFlashAttribute("categoryBindingModel", categoryBindingModel)
                    .addFlashAttribute("org.springframework.validation.BindingResult.categoryBindingModel", bindingResult);
            return "redirect:/admin/categories/create";
        }
        categoryBindingModel.setFieldNames(fieldNames);
        categoryBindingModel.setFieldSlugs(fieldSlugs);
        categoryService.store(categoryBindingModel);
        return "redirect:/admin/categories";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable Long id, Model model) {
        CategoryBindingModel categoryBindingModel = categoryService.getCategoryBindingModel(id);

        model.addAttribute("categoryBindingModel", categoryBindingModel);
        model.addAttribute("categoryFieldsBindingModels", categoryBindingModel.getCategoryFields());
        return "admin/categories/edit";
    }

    @PutMapping("/{id}/edit")
    public String update(@PathVariable Long id,
                         @Valid CategoryBindingModel categoryBindingModel,
                         @RequestParam(value = "field_name[]") String[] fieldNames,
                         @RequestParam(value = "field_slug[]") String[] fieldSlugs,
                         BindingResult bindingResult,
                         RedirectAttributes redirectAttributes) throws Exception {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("categoryBindingModel", categoryBindingModel)
                    .addFlashAttribute("org.springframework.validation.BindingResult.categoryBindingModel", bindingResult);
            return "redirect:/admin/categories/" + categoryBindingModel.getId() +"/edit";
        }
        categoryBindingModel.setFieldNames(fieldNames);
        categoryBindingModel.setFieldSlugs(fieldSlugs);
        categoryService.update(categoryBindingModel);
        return "redirect:/admin/categories" ;
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) throws Exception {
        categoryService.delete(id);
        return "redirect:/admin/categories";
    }
}
