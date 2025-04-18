package com.example.accounter.user;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/v1/user")
@AllArgsConstructor
public class UserController {
    // private UserRequest userRequest;
    private UserService userService;

    @GetMapping(value = "/getusername")
    public String currentUserName(Authentication auth) {
        AppUser user = (AppUser) auth.getPrincipal();   
        return user.toString();
    }

    @PostMapping(path = "/register", consumes = "application/json")
    public String register(@RequestBody UserRequest request){
        AppUser user = new AppUser(request.getEmail(), request.getEmail(), request.getPassword(), UserRole.USER);

        return userService.signUpUser(user);
    }

}
