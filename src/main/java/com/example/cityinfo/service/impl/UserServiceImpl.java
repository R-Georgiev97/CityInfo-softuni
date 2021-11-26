package com.example.cityinfo.service.impl;

import com.example.cityinfo.model.entity.Role;
import com.example.cityinfo.model.entity.User;
import com.example.cityinfo.model.entity.enums.UserRoleEnum;
import com.example.cityinfo.model.service.UserRegistrationServiceModel;
import com.example.cityinfo.repository.RoleRepository;
import com.example.cityinfo.repository.UserRepository;
import com.example.cityinfo.service.UserService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final AuthServiceImpl authService;

    public UserServiceImpl(PasswordEncoder passwordEncoder,
                           UserRepository userRepository,
                           RoleRepository roleRepository,
                           AuthServiceImpl authService) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.authService = authService;
    }

    @Override
    public void initializeUsersAndRoles() {
        initializeRoles();
        initializeUsers();
    }

    private void initializeUsers() {
        if (userRepository.count() == 0) {

            Role adminRole = roleRepository.findByRole(UserRoleEnum.ADMIN);
            Role userRole = roleRepository.findByRole(UserRoleEnum.USER);

            User admin = new User();
            admin
                    .setUsername("admin")
                    .setPassword(passwordEncoder.encode("123456"))
                    .setFirstName("Admin")
                    .setLastName("Adminov")
                    .setEmail("admin@cityInfo.bg");

            admin.setRole(adminRole);
            userRepository.save(admin);


        }
    }

    private void initializeRoles() {

        if (roleRepository.count() == 0) {
            Role adminRole = new Role();
            adminRole.setRole(UserRoleEnum.ADMIN);

            Role userRole = new Role();
            userRole.setRole(UserRoleEnum.USER);

            roleRepository.saveAll(List.of(adminRole, userRole));
        }
    }

    @Override
    public void registerAndLoginUser(UserRegistrationServiceModel userRegistrationServiceModel) {

        Role userRole = roleRepository.findByRole(UserRoleEnum.USER);

        User newUser = new User();

        newUser.
                setUsername(userRegistrationServiceModel.getUsername()).
                setFirstName(userRegistrationServiceModel.getFirstName()).
                setLastName(userRegistrationServiceModel.getLastName()).
                setEmail(userRegistrationServiceModel.getEmail()).
                setPassword(passwordEncoder.encode(userRegistrationServiceModel.getPassword())).
                setRole(userRole);

        newUser = userRepository.save(newUser);

        UserDetails principal = authService.loadUserByUsername(newUser.getUsername());
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                principal,
                newUser.getPassword(),
                principal.getAuthorities()
        );

        SecurityContextHolder.
                getContext().
                setAuthentication(authentication);
    }

    public boolean isUserNameFree(String username) {
        return userRepository.findByUsernameIgnoreCase(username).isEmpty();
    }

    @Override
    public boolean isUserEmailFree(String email) {
        return userRepository.findByEmailIgnoreCase(email).isEmpty();

    }
}