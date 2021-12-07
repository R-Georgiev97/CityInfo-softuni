package com.example.cityinfo.model.binding;

import com.example.cityinfo.model.entity.enums.UserRoleEnum;

import javax.validation.constraints.*;

public class UserEditBindingModel {

    private Long id;

    @NotNull
    @Size(min = 4, max = 20)
    private String firstName;

    @NotNull
    @Size(min = 4, max = 20)
    private String lastName;

    @NotEmpty
    @Email
    private String email;

    @NotBlank
    @Size(min = 4, max = 20)
    private String username;

    private UserRoleEnum roleName;

    public Long getId() {
        return id;
    }

    public UserEditBindingModel setId(Long id) {
        this.id = id;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public UserEditBindingModel setFirstName(String firstName) {
        this.firstName = firstName;
        return this;

    }

    public String getLastName() {
        return lastName;
    }

    public UserEditBindingModel setLastName(String lastName) {
        this.lastName = lastName;
        return this;

    }

    public String getEmail() {
        return email;
    }

    public UserEditBindingModel setEmail(String email) {
        this.email = email;
        return this;

    }

    public String getUsername() {
        return username;
    }

    public UserEditBindingModel setUsername(String username) {
        this.username = username;
        return this;

    }

    public UserRoleEnum getRoleName() {
        return roleName;
    }

    public UserEditBindingModel setRoleName(UserRoleEnum roleName) {
        this.roleName = roleName;
        return this;

    }
}
