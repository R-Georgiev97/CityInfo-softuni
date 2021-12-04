package com.example.cityinfo.web.admin;

import com.example.cityinfo.model.binding.CategoryBindingModel;
import com.example.cityinfo.model.binding.UserEditBindingModel;
import com.example.cityinfo.model.binding.UserRegistrationBindingModel;
import com.example.cityinfo.service.UserService;
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
@RequestMapping("/admin/users")
public class UserController {

    static final String TEMPLATE_DIRECTORY = "admin/users";

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("users", userService.getAllUsersWithRoles());
        return TEMPLATE_DIRECTORY + "/index";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable Long id, Model model) throws Exception {
        UserEditBindingModel userEditBindingModel = userService.getUserEditBindingModel(id);
        model.addAttribute("userEditBindingModel", userEditBindingModel);
        return TEMPLATE_DIRECTORY + "/edit";
    }

    @PutMapping("/{id}/edit")
    public String update(@PathVariable Long id,
                         @Valid UserEditBindingModel userEditBindingModel,
                         BindingResult bindingResult,
                         RedirectAttributes redirectAttributes) throws Exception {
        if (bindingResult.hasErrors()) {
            System.out.println("errors " + bindingResult.getAllErrors());
            redirectAttributes.addFlashAttribute("userEditBindingModel", userEditBindingModel)
                    .addFlashAttribute("org.springframework.validation.BindingResult.userEditBindingModel", bindingResult);
            return "redirect:/" + TEMPLATE_DIRECTORY + "/" + userEditBindingModel.getId() + "/edit";
        }

        userService.update(userEditBindingModel);
        return "redirect:/" + TEMPLATE_DIRECTORY;
    }

    @PostMapping(value = "/{id}/delete", consumes = "application/json", produces = "application/json")
    public void delete(@PathVariable Long id, HttpServletResponse response) throws Exception {
        userService.delete(id);
        response.setStatus(200);
    }


}
