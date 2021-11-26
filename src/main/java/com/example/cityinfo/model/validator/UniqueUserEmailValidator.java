package com.example.cityinfo.model.validator;

import com.example.cityinfo.service.UserService;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueUserEmailValidator  implements ConstraintValidator<UniqueUserEmail, String> {

    private final UserService userService;

    public UniqueUserEmailValidator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean isValid(String username, ConstraintValidatorContext context) {
        if (username == null) {
            return true;
        }
        return userService.isUserEmailFree(username);
    }
}
