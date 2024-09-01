package com.teachercrudapp.TeacherCurdApp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class Login {

    @GetMapping
    public String showLoginPage(){
        return "login";
    }

    @PostMapping
    public String submitLogin(){
        return "redirect:/welcome";
    }

}
