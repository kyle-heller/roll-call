package com.rollcall.web.services.impl;

import com.rollcall.web.dto.RegistrationDto;
import com.rollcall.web.models.Role;
import com.rollcall.web.models.UserEntity;
import com.rollcall.web.repository.RoleRepository;
import com.rollcall.web.repository.UserRepository;
import com.rollcall.web.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;


    @Override // Registers a new user with details from RegistrationDto, encodes the password, assigns the USER role, and saves to the database
    public void saveUser(RegistrationDto registrationDTO) {
        UserEntity user = new UserEntity();
        user.setUsername(registrationDTO.getUsername());
        user.setEmail(registrationDTO.getEmail());
        user.setPassword(passwordEncoder.encode(registrationDTO.getPassword()));
        Role role = roleRepository.findByName("USER");
        user.setRoles(Arrays.asList(role));
        userRepository.save(user);
    }

    @Override // Finds a user by their email and returns the UserEntity, or null if not found
    public UserEntity findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override // Finds a user by their username and returns the UserEntity, or null if not found
    public UserEntity findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}