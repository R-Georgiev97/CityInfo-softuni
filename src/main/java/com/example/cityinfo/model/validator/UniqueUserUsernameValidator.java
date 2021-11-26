package com.example.cityinfo.model.validator;

import com.example.cityinfo.service.UserService;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueUserUsernameValidator implements ConstraintValidator<UniqueUserUsername, String> {

    private final UserService userService;

    public UniqueUserUsernameValidator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean isValid(String username, ConstraintValidatorContext context) {
        if (username == null) {
            return true;
        }
        return userService.isUserNameFree(username);
    }
}
