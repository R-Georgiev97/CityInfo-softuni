package com.example.cityinfo.web;


import com.example.cityinfo.model.binding.CommentBindingModel;
import com.example.cityinfo.model.binding.ObjectBindingModel;
import com.example.cityinfo.service.CommentService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Map;

@Controller
@RequestMapping("/comments")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping
    public String store(@Valid CommentBindingModel commentBindingModel,
                        HttpServletRequest request,
                        BindingResult bindingResult,
                        RedirectAttributes redirectAttributes){
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("commentBindingModel", commentBindingModel)
                    .addFlashAttribute("org.springframework.validation.BindingResult.commentBindingModel", bindingResult);
            String referer = request.getHeader("Referer");
            return "redirect:"+ referer;
        }

        commentService.store(commentBindingModel);

        String referer = request.getHeader("Referer");
        return "redirect:"+ referer;
    }
}
