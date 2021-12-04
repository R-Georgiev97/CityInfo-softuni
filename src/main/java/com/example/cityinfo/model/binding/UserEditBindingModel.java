package com.example.cityinfo.model.binding;

import com.example.cityinfo.model.entity.enums.UserRoleEnum;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserEditBindingModel {

    private Long id;

    @NotNull
    @Size(min = 4, max = 20)
    private String firstName;

    @NotNull
    @Size(min = 4, max = 20)
    private String lastName;

    @NotNull
    @Email
    private String email;

    @NotBlank
    @Size(min = 4, max = 20)
    private String username;

    private UserRoleEnum roleName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public UserRoleEnum getRoleName() {
        return roleName;
    }

    public void setRoleName(UserRoleEnum roleName) {
        this.roleName = roleName;
    }
}
