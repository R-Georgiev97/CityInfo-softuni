package com.example.cityinfo.model.view;

import com.example.cityinfo.model.entity.Role;
import com.example.cityinfo.model.entity.enums.UserRoleEnum;

public class UserWithRoleView {
    private Long id;
    private String email;
    private String username;
    private String firstName;
    private String lastName;
    private UserRoleEnum role;
    private String fullName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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


    public UserRoleEnum getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role.getRole();
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }
}
