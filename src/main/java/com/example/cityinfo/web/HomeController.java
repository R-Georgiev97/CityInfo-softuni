package com.example.cityinfo.web;

import com.example.cityinfo.model.binding.ObjectBindingModel;
import com.example.cityinfo.service.ObjectService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    private final ObjectService objectService;

    public HomeController(ObjectService objectService) {
        this.objectService = objectService;
    }

    @GetMapping()
    public String index(Model model){
        List<ObjectBindingModel> objectBindingModels = objectService.getLastApprovedObjects();
        model.addAttribute("objectBindingModels", objectBindingModels);
        return "home";
    }

    @GetMapping("/about")
    public String about(Model model){
        return "home";
    }
}
