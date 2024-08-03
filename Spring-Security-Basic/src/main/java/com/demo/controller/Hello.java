package com.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Hello {


   @GetMapping("/hello")
    public String hello()
    {
        return "Welcome to Spring Security Demo";
    }

    @GetMapping("/user")
    public String greetUser()
    {
        return "Hello User";
    }

    @GetMapping("/admin")
    public String greetAdmin()
    {
        return "Hello Admin";
    }

}
