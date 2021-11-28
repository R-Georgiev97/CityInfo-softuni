package com.example.cityinfo.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/objects")
public class ObjectController {

    @GetMapping()
    public String index(Model model){
        //TODO return all objects
        return "objects/index";
    }

    @GetMapping("/create")
    public String create(Model model){
        //TODO
        return "objects/create";
    }

}
