package com.example.accounter.user;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @GetMapping(value = "/userdetails")
    public String currentUserName(Authentication auth) {
        AppUser user = (AppUser) auth.getPrincipal();   
        return user.toString();
    }

}
