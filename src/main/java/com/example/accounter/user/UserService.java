package com.example.accounter.user;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {
    private final static String USER_NOT_FOUND_MSG = "User with email %s not found";
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email).orElseThrow(() -> 
                            new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG, email)));
    }

    public String signUpUser(AppUser user){
        boolean userExists = userRepository.findByEmail(user.getEmail()).isPresent();

        if (userExists){
            throw new IllegalStateException("email already exists");
        }
        
        String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        
        userRepository.save(user);
        //TODO: Send confirmation token
        
        return "it works";
    }

    public AppUser getSessionUser(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        AppUser user =  (AppUser) auth.getPrincipal();

        return user;
    }

    // public AppUser

    
}
