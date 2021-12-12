package com.example.cityinfo.web.admin;

import com.example.cityinfo.service.StatsService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@PreAuthorize("hasRole('ROLE_ADMIN')")
@RequestMapping("/admin/statistics")
public class StatsController {
    private final StatsService statsService;

    public StatsController(StatsService statsService) {
        this.statsService = statsService;
    }

    @GetMapping()
    public ModelAndView statistics() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("stats", statsService.getStats());
        modelAndView.setViewName("/admin/statistics/index");
        return modelAndView;
    }
}
