package com.example.cityinfo.web;

import com.example.cityinfo.model.binding.CityBindingModel;
import com.example.cityinfo.model.binding.ObjectBindingModel;
import com.example.cityinfo.model.view.CategoryNameAndSlugView;
import com.example.cityinfo.service.CategoryService;
import com.example.cityinfo.service.CityService;
import com.example.cityinfo.service.ObjectService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/objects")
public class ObjectController {

    private final CityService cityService;
    private final ObjectService objectService;
    private final CategoryService categoryService;

    public ObjectController(CityService cityService, ObjectService objectService, CategoryService categoryService) {
        this.cityService = cityService;
        this.objectService = objectService;
        this.categoryService = categoryService;
    }

    @GetMapping()
    public String index(Model model){
        //TODO return all objects
        return "objects/index";
    }


    @ModelAttribute("objectBindingModel")
    public ObjectBindingModel objectBindingModel() {
        return new ObjectBindingModel();
    }

    @GetMapping("/create")
    public String create(Model model){
        List<CityBindingModel> cities = cityService.getAllCities();
        List<CategoryNameAndSlugView> categories = categoryService.getAllCategories();
        model.addAttribute("cities",cities);
        model.addAttribute("categories",categories);
        return "objects/create";
    }

    @PostMapping
    public String store(@Valid ObjectBindingModel objectBindingModel,
                        @RequestParam Map<String,String> allRequestParams,
                        BindingResult bindingResult,
                        RedirectAttributes redirectAttributes){
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("objectBindingModel", objectBindingModel)
                    .addFlashAttribute("org.springframework.validation.BindingResult.objectBindingModel", bindingResult);
            return "redirect:/objects/create";
        }

        objectService.store(objectBindingModel, allRequestParams);

        return "objects/index";
    }

}
