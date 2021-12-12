package com.example.cityinfo.web;

import com.example.cityinfo.model.binding.CityBindingModel;
import com.example.cityinfo.model.binding.ObjectBindingModel;
import com.example.cityinfo.model.binding.ObjectEditBindingModel;
import com.example.cityinfo.model.view.CategoryNameAndSlugView;
import com.example.cityinfo.model.view.CommentViewModel;
import com.example.cityinfo.model.view.ObjectViewModel;
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

        List<CityBindingModel> cities = cityService.getAllCities();
        List<CategoryNameAndSlugView> categories = categoryService.getAllCategories();
        List<ObjectViewModel> objects = objectService.getAllApproved();

        model.addAttribute("cities",cities);
        model.addAttribute("categories",categories);
        model.addAttribute("objects",objects);
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

    @GetMapping("/{id}")
    public String show(@PathVariable Long id, Model model) throws Exception {
        model.addAttribute("object", objectService.getById(id));
        return "objects/show";
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

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable Long id, Model model) throws Exception {
        ObjectViewModel objectViewModel = objectService.getById(id);
        model.addAttribute("object", objectViewModel);
        return "objects/edit";
    }

    @PutMapping("/{id}/edit")
    public String update(@PathVariable Long id,
                         @Valid ObjectEditBindingModel objetEditBindingModel,
                         @RequestParam Map<String,String> allRequestParams,
                         BindingResult bindingResult,
                         RedirectAttributes redirectAttributes) throws Exception {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("object", objetEditBindingModel)
                    .addFlashAttribute("org.springframework.validation.BindingResult.object", bindingResult);
            return "redirect:/objects/"+  objetEditBindingModel.getId() +"/edit";
        }
        objectService.update(objetEditBindingModel, allRequestParams);
        return "redirect:/objects" ;
    }

}
