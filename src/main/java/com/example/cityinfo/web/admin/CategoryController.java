package com.example.cityinfo.web.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/categories")
public class CategoryController {

    static final String TEMPLATE_DIRECTORY = "admin/categories/";

    @GetMapping()
    public String index(Model model){
        //TODO return all objects
        return TEMPLATE_DIRECTORY + "index";
    }

    @GetMapping("/create")
    public String create(Model model){
        //TODO
        return TEMPLATE_DIRECTORY + "create";
    }

}
