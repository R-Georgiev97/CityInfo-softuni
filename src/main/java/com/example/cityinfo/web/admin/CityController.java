package com.example.cityinfo.web.admin;

import com.example.cityinfo.model.binding.CategoryBindingModel;
import com.example.cityinfo.model.binding.CityBindingModel;
import com.example.cityinfo.service.CityService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Controller
@PreAuthorize("hasRole('ROLE_ADMIN')")
@RequestMapping("/admin/cities")
public class CityController {

    static final String TEMPLATE_DIRECTORY = "admin/cities";
    private final CityService cityService;

    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("cities", cityService.getAllCities());
        return TEMPLATE_DIRECTORY + "/index";
    }

    @GetMapping("/create")
    public String create(Model model){
        model.addAttribute("cityBindingModel", new CityBindingModel());
        return  TEMPLATE_DIRECTORY + "/create";
    }


    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping()
    public String store(@Valid CityBindingModel cityBindingModel,
                        BindingResult bindingResult,
                        RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {

            redirectAttributes.addFlashAttribute("cityBindingModel", cityBindingModel)
                    .addFlashAttribute("org.springframework.validation.BindingResult.cityBindingModel", bindingResult);
            return "redirect:/" + TEMPLATE_DIRECTORY + "/create";
        }

        cityService.store(cityBindingModel);
        return "redirect:/" + TEMPLATE_DIRECTORY;
    }

    @DeleteMapping(value = "/{id}", consumes = "application/json", produces = "application/json")
    public void destroy(@PathVariable Long id, HttpServletResponse response ) {
        cityService.destroy(id);
        response.setStatus(200);
    }
}
