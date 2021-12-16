package com.example.cityinfo.service.impl;

import com.example.cityinfo.model.entity.Role;
import com.example.cityinfo.model.entity.User;
import com.example.cityinfo.model.entity.enums.UserRoleEnum;
import com.example.cityinfo.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@ExtendWith(MockitoExtension.class)
public class AuthServiceImplTest {
    private User testUser;
    private Role adminRole, userRole;

    private AuthServiceImpl AuthServiceImpl;

    @Mock
    private UserRepository mockUserRepository;

    @BeforeEach
    void init() {

        //ARRANGE
        AuthServiceImpl = new AuthServiceImpl(mockUserRepository);

        adminRole = new Role();
        adminRole.setRole(UserRoleEnum.ADMIN.ADMIN);

        userRole = new Role();
        userRole.setRole(UserRoleEnum.USER);

        testUser = new User();
        testUser.setUsername("lucho");
        testUser.setEmail("lucho@lucho.com");
        testUser.setPassword("topsecret");
        testUser.setRole(adminRole);
    }

    @Test
    void testUserNotFound() {
        Assertions.assertThrows(
                UsernameNotFoundException.class,
                () -> AuthServiceImpl.loadUserByUsername("invalid_user_email@not-exist.com")
        );
    }

    @Test
    void testUserFound() {

        // Arrange
        Mockito.when(mockUserRepository.findByUsername(testUser.getEmail())).
                thenReturn(Optional.of(testUser));

        // Act
        var actual = AuthServiceImpl.loadUserByUsername(testUser.getEmail());

        // Assert

        String expectedRoles = "ROLE_ADMIN";
        String actualRoles = actual.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(
                Collectors.joining(", "));

        Assertions.assertEquals(actual.getUsername(), testUser.getUsername());
        Assertions.assertEquals(expectedRoles, actualRoles);
    }
}
