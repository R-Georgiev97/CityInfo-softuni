package com.example.cityinfo.service;

import com.example.cityinfo.model.service.UserRegistrationServiceModel;

public interface UserService {

    void initializeUsersAndRoles();

    void registerAndLoginUser(UserRegistrationServiceModel userRegistrationServiceModel);
    boolean isUserNameFree(String username);
    boolean isUserEmailFree(String email);
}
