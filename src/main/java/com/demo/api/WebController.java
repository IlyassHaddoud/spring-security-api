package com.demo.api;

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
        return "This should be private!!";
    }
}
