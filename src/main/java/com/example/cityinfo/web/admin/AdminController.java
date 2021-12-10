package com.example.cityinfo.web.admin;

import com.example.cityinfo.service.ObjectService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@PreAuthorize("hasRole('ROLE_ADMIN')")
@RequestMapping("/admin")
public class AdminController {

    private final ObjectService objectService;

    public AdminController(ObjectService objectService) {
        this.objectService = objectService;
    }

    @GetMapping()
    public String index(Model model){
        return  "admin/index";
    }

    @GetMapping("/objects")
    public String objects(Model model) {
        model.addAttribute("objects", objectService.getAllNotApproved());
        return  "admin/objects/index";
    }
}
