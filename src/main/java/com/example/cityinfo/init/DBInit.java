package com.example.cityinfo.init;

import com.example.cityinfo.service.CategoryService;
import com.example.cityinfo.service.CityService;
import com.example.cityinfo.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DBInit  implements CommandLineRunner {

    private final UserService userService;
    private final CategoryService categoryService;
    private final CityService cityService;

    public DBInit(UserService userService, CategoryService categoryService, CityService cityService) {
        this.userService = userService;
        this.categoryService = categoryService;
        this.cityService = cityService;
    }

    @Override
    public void run(String... args) throws Exception {
        userService.initializeUsersAndRoles();
        categoryService.seedCategoryWithFields();
        cityService.seedCities();
    }
}
