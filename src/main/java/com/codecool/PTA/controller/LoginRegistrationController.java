package com.codecool.PTA.controller;

import com.codecool.PTA.model.user.Student;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

//TODO: login-logout might end up in a @RestController instead?
@Controller
public class LoginRegistrationController {

    @GetMapping("registration")
    public String displayRegistrationForm(Model model) {
        return "registration/registration";
    }

    @PostMapping("registration")
    public String registerNewStudent(@ModelAttribute Student student) {
        return "redirect:index";
    }

    @GetMapping("login")
    public String displayLoginForm(Model model) {
        return "login/login";
    }

    @PostMapping("login")
    public String loginStudent(@ModelAttribute Student student) {
        return "redirect:index";
    }

    @GetMapping("logout")
    public String logoutStudent() {
        return "redirect:index";
    }

}
