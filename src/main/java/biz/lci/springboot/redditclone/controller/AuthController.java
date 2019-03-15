package biz.lci.springboot.redditclone.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthController {
    @GetMapping("/login")
    public String login() {
        return "auth/login";
    }

    @GetMapping("/profile")
    public String showProfile() {
        return "auth/profile";
    }

    @GetMapping("/register")
    public String showRegister() {
        return "auth/register";
    }
}
