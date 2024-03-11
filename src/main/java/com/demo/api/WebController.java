package com.demo.api;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebController {
    @GetMapping("/")
    public String publicPage()
    {
        return "Hello There!!";
    }
    @GetMapping("private")
    public String privatePage()
    {
        String principal = SecurityContextHolder.getContext().getAuthentication().getName();
        return "Welcome to the VIP room ["+principal+"] 💎💵";
    }
    @GetMapping("admin")
    public String adminPage()
    {
        return "Hello from admin page";
    }

}
