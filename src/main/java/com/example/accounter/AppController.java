package com.example.accounter;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AppController {
    
    @RequestMapping("/")
    public String index(){
        return "index";
    }

    @RequestMapping("/signup")
    public String signup(){
        return "signup";
    }
}

