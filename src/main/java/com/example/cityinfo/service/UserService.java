package com.example.cityinfo.service;

import com.example.cityinfo.model.binding.UserEditBindingModel;
import com.example.cityinfo.model.entity.User;
import com.example.cityinfo.model.service.UserRegistrationServiceModel;
import com.example.cityinfo.model.view.UserWithRoleView;

import java.util.List;

public interface UserService {

    void initializeUsersAndRoles();

    void registerAndLoginUser(UserRegistrationServiceModel userRegistrationServiceModel);
    boolean isUserNameFree(String username);
    boolean isUserEmailFree(String email);

    void delete(Long id) throws Exception;

    List<UserWithRoleView> getAllUsersWithRoles();

    UserEditBindingModel getUserEditBindingModel(Long id) throws Exception;

    void update(UserEditBindingModel userEditBindingModel) throws Exception;

    User getByUsername(String username);
}
