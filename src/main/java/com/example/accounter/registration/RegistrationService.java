package com.example.accounter.registration;

import org.springframework.stereotype.Service;

import com.example.accounter.user.AppUser;
import com.example.accounter.user.UserRole;
import com.example.accounter.user.UserService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RegistrationService {

    private final UserService userService;

    public String register(RegistrationRequest request) {
        
        return userService.signUpUser(new AppUser(request.getEmail(), request.getEmail(), request.getPassword(), UserRole.USER));
    }
}
